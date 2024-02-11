package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;
import util.assertion.Argument;

public class AddMechanic implements Command<MechanicBLDto> {
	MechanicBLDto mechanic = null;
	MechanicGateway mg = PersistenceFactory.forMechanic();

	public AddMechanic(MechanicBLDto m) {
		Argument.isNotNull(m);
		Argument.isNotEmpty(m.dni);
		this.mechanic = m;

	}

	@Override
	public MechanicBLDto execute() throws BusinessException {
		if (existsMechanic(mechanic.dni)) {
			throw new BusinessException("Mechanic already exists");
		}
		mechanic.id = UUID.randomUUID().toString();
		mg.add(MechanicAssembler.toDALDto(mechanic));
		return mechanic;
	}

	private boolean existsMechanic(String dni) {
		Optional<MechanicDALDto> mechanic = mg.findByDni(dni);
		return mechanic.isPresent();
	}
}