package uo.ri.cws.application.service.workorder.impl.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.assertion.ArgumentChecks;

public class AssignWorkOrder implements Command<Void> {

	private String woId;
	private String mDni;

	public AssignWorkOrder(String woId, String mDni) {
		ArgumentChecks.isNotBlank(woId, "Work order id can't be empty");
		ArgumentChecks.isNotBlank(mDni, "Mechanic dni can't be empty");
		this.woId = woId;
		this.mDni = mDni;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<WorkOrder> wos = Factory.repository.forWorkOrder()
				.findById(woId);
		BusinessChecks.isTrue(wos.isPresent(), "Work order doesn't exist");
		Optional<Mechanic> mo = Factory.repository.forMechanic()
				.findByDni(mDni);
		BusinessChecks.isTrue(mo.isPresent(), "Mechanic doesn't exist");
		WorkOrder wo = wos.get();
		Mechanic m = mo.get();
		isMechanicCertified(m, wo);
		BusinessChecks.isTrue(wo.isOpen(), "Invalid work order status");
		wo.assignTo(mo.get());
		return null;
	}

	private void isMechanicCertified(Mechanic m, WorkOrder wo)
			throws BusinessException {
		for (Certificate c : m.getCertificates()) {
			if (c.getVehicleType().equals(wo.getVehicle().getVehicleType())) {
				return;
			}
		}
		throw new BusinessException(
				"Mechanic is not certified for this vehicle type");

	}
}
