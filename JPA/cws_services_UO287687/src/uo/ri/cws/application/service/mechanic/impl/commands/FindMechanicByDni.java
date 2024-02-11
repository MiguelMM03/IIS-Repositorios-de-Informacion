package uo.ri.cws.application.service.mechanic.impl.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.service.mechanic.impl.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.util.assertion.ArgumentChecks;

public class FindMechanicByDni implements Command<Optional<MechanicDto>> {

	private String dni;

	public FindMechanicByDni(String dni) {
		ArgumentChecks.isNotBlank(dni, "Mechanic dni can't be empty");
		this.dni = dni;
	}

	@Override
	public Optional<MechanicDto> execute() throws BusinessException {
		return Factory.repository.forMechanic().findByDni(dni)
				.map(m -> DtoAssembler.toDto(m));
	}

}
