package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import util.assertion.Argument;

public class FindMechanicById implements Command<Optional<MechanicBLDto>> {

	MechanicGateway mg = PersistenceFactory.forMechanic();
	String mechanicId;

	public FindMechanicById(String id) {
		Argument.isNotNull(id);
		Argument.isNotEmpty(id);
		this.mechanicId = id;

	}

	@Override
	public Optional<MechanicBLDto> execute() {
		return MechanicAssembler.toBLDto(mg.findById(mechanicId));

	}
}
