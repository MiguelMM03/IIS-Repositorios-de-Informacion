package uo.ri.cws.application.business.workorder.crud.commands;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.business.certificate.assembler.CertificateAssembler;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.vehicle.assembler.VehicleAssembler;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.business.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.certificate.CertificateGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import util.assertion.Argument;

public class AssignWorkOrderToMechanic implements Command<Void> {

	private String woID;
	private String mechanicDni;
	private WorkOrderBLDto workorder;
	private MechanicBLDto mechanic;

	private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();
	private MechanicGateway mg = PersistenceFactory.forMechanic();
	private CertificateGateway cg = PersistenceFactory.forCertificate();
	private VehicleGateway vg = PersistenceFactory.forVehicle();

	public AssignWorkOrderToMechanic(String woID, String mechanicDni) {
		Argument.isNotEmpty(mechanicDni);
		Argument.isNotEmpty(woID);
		this.woID = woID;
		this.mechanicDni = mechanicDni;
	}

	@Override
	public Void execute() throws BusinessException {
		checkMechanic();
		checkWorkOrder();
		checkCertified();
		workorder.mechanicId=mechanic.id;
		workorder.status="ASSIGNED";
		wog.update(WorkOrderAssembler.toWorkOrderDALDto(workorder));
		return null;
	}

	private void checkWorkOrder() throws BusinessException {

		if (wog.findById(woID).isEmpty()) {
			throw new BusinessException("Work order doesn't exist");
		}
		this.workorder = WorkOrderAssembler.toWorkOrderDto(wog.findById(woID).get());
		if (!workorder.status.equalsIgnoreCase("OPEN")) {
			throw new BusinessException("Wrong status for the work order");
		}

	}

	private void checkMechanic() throws BusinessException {
		Optional<MechanicDALDto> m = mg.findByDni(mechanicDni);
		if (m.isEmpty()) {
			throw new BusinessException("Mechanic doesn't exist");
		}
		this.mechanic = MechanicAssembler.toBLDto(m).get();

	}

	private void checkCertified() throws BusinessException {
		VehicleBLDto vehicle = getVehicle(workorder.vehicleId);
		List<CertificateBLDto> list = CertificateAssembler
				.toCertificateDtoList(cg.findCertificatesByVehicleTypeId(vehicle.vehicleTypeId));
		boolean contains = false;
		for (CertificateBLDto c : list) {
			if (c.mechanic.dni.equals(mechanicDni)) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			throw new BusinessException("Mechanic can´t do the workorder");
		}
	}

	private VehicleBLDto getVehicle(String vehicleId) throws BusinessException {
		Optional<VehicleDALDto> dald = vg.findById(vehicleId);
		if (dald.isEmpty()) {
			throw new BusinessException("Vehicle doesn't exist");
		}
		return VehicleAssembler.toVehicleDto(dald.get());
	}

}
