package uo.ri.cws.application.service.mechanic.impl.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

public class DeleteMechanic implements Command<Void> {

	private String mechanicId;
	private MechanicRepository mr = Factory.repository.forMechanic();

	public DeleteMechanic(String mechanicId) {
		ArgumentChecks.isNotEmpty(mechanicId, "Mechanic id can't be empty");
		this.mechanicId = mechanicId;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<Mechanic> m = mr.findById(mechanicId);
		if (m.isEmpty()) {
			throw new BusinessException("Mechanic doesn't exist");
		}
		Mechanic mechanic = m.get();
		BusinessChecks.isTrue(mechanic.getInterventions().size() == 0,
				"Mechanic has interventions");
		BusinessChecks.isTrue(mechanic.getAssigned().size() == 0,
				"Mechanic has assigned workorders");
		mr.remove(mechanic);
		return null;
	}

}
