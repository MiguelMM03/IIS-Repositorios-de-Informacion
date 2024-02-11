package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import util.assertion.Argument;

public class FindMechanicByDni implements Command<Optional<MechanicBLDto>> {
	MechanicGateway mg = PersistenceFactory.forMechanic();
	String mechanicDni;

	public FindMechanicByDni(String dni) {
		Argument.isNotNull(dni);
		Argument.isNotEmpty(dni);
		this.mechanicDni = dni;

	}

	@Override
	public Optional<MechanicBLDto> execute() throws BusinessException {
		return MechanicAssembler.toBLDto(mg.findByDni(mechanicDni));

	}
}
