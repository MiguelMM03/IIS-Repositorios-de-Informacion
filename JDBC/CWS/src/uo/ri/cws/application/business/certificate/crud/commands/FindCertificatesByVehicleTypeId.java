package uo.ri.cws.application.business.certificate.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.business.certificate.assembler.CertificateAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.certificate.CertificateGateway;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway;
import util.assertion.Argument;

public class FindCertificatesByVehicleTypeId implements Command<List<CertificateBLDto>> {

	private CertificateGateway cg = PersistenceFactory.forCertificate();
	private VehicleTypeGateway vtg = PersistenceFactory.forVehicleType();
	private String vehicleTypeId;

	public FindCertificatesByVehicleTypeId(String vehicleTypeId) {
		Argument.isNotEmpty(vehicleTypeId);
		this.vehicleTypeId = vehicleTypeId;
	}

	@Override
	public List<CertificateBLDto> execute() throws BusinessException {
		checkVehicleType();
		return CertificateAssembler.toCertificateDtoList(cg.findCertificatesByVehicleTypeId(vehicleTypeId));
	}

	private void checkVehicleType() throws BusinessException {
		if (vtg.findById(vehicleTypeId).isEmpty()) {
			throw new BusinessException("Vehicle type doesn't exist");
		}

	}

}
