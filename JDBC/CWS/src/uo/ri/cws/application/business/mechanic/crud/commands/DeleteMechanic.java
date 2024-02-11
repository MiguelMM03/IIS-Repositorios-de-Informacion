package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;
import util.assertion.Argument;

public class DeleteMechanic implements Command<Void> {

	MechanicGateway mg = PersistenceFactory.forMechanic();
	String mechanicId;

	public DeleteMechanic(String id) {
		Argument.isNotEmpty(id);
		this.mechanicId = id;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<MechanicDALDto> mechanic = mg.findById(mechanicId);
		if (mechanic.isPresent() && !hasWorkOrdersAssigned(mechanicId)) {
			mg.remove(mechanicId);
		} else {
			throw new BusinessException("Mechanic doesn't exists");
		}
		return null;
	}

	private boolean hasWorkOrdersAssigned(String mechanicId) {
		List<WorkOrderDALDto> workorders = PersistenceFactory.forWorkOrder().findByMechanic(mechanicId);
		return workorders.size() != 0;
	}
}
