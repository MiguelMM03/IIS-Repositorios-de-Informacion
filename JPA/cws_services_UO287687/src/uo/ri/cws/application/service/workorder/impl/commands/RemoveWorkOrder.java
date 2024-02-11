package uo.ri.cws.application.service.workorder.impl.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.assertion.ArgumentChecks;

public class RemoveWorkOrder implements Command<Void> {

	private String id;

	public RemoveWorkOrder(String id) {
		ArgumentChecks.isNotBlank(id, "Work order id can't be empty");
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		Factory.repository.forWorkOrder().remove(getWorkOrder());
		return null;
	}

	private WorkOrder getWorkOrder() throws BusinessException {
		Optional<WorkOrder> wos = Factory.repository.forWorkOrder()
				.findById(id);
		BusinessChecks.isTrue(wos.isPresent(), "Work order doesn't exist");
		WorkOrder wo = wos.get();
		BusinessChecks.isTrue(wo.getInterventions().size() == 0,
				"Work order has interventions");
		return wo;
	}
}
