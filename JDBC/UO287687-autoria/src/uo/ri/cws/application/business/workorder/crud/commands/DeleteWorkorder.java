package uo.ri.cws.application.business.workorder.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;
import util.assertion.Argument;

public class DeleteWorkorder implements Command<Void> {

	private String id;
	private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();

	public DeleteWorkorder(String id) {
		Argument.isNotEmpty(id);
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<WorkOrderDALDto> workorder = wog.findById(id);
		if (workorder.isPresent()) {
			checkWorkOrder(workorder.get());
			wog.remove(id);
		} else {
			throw new BusinessException("Work order doesn't exists");
		}
		return null;
	}

	private void checkWorkOrder(WorkOrderDALDto wo) throws BusinessException {
		if (!PersistenceFactory.forIntervention().findInterventionsByWorkOrderId(wo.id).isEmpty()) {
			throw new BusinessException("Work order has interventions");
		}

	}

}
