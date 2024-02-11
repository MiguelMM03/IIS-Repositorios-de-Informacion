package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;
import util.assertion.Argument;

public class UpdateMechanic implements Command<Void> {

	MechanicGateway mg = PersistenceFactory.forMechanic();
	MechanicBLDto mechanic = null;

	public UpdateMechanic(MechanicBLDto m) {
		Argument.isNotNull(m);
		Argument.isNotEmpty(m.id);
		// Argument.isNotEmpty(m.dni);
		Argument.isNotEmpty(m.name);
		Argument.isNotEmpty(m.surname);
		this.mechanic = m;
	}

	@Override
	public Void execute() throws BusinessException {
		if (!existsMechanic(mechanic.id)) {
			throw new BusinessException("Mechanic does not exists");
		}
		mg.update(MechanicAssembler.toDALDto(mechanic));
		return null;

	}

	private boolean existsMechanic(String id) {
		Optional<MechanicDALDto> mechanic = mg.findById(id);
		return mechanic.isPresent();
	}
}
