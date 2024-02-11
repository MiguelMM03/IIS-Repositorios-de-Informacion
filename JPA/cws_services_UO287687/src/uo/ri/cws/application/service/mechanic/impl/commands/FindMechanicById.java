package uo.ri.cws.application.service.mechanic.impl.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.service.mechanic.impl.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.util.assertion.ArgumentChecks;

public class FindMechanicById implements Command<Optional<MechanicDto>> {

	private String id;

	public FindMechanicById(String id) {
		ArgumentChecks.isNotBlank(id, "Mechanic id can't be empty");
		this.id = id;
	}

	@Override
	public Optional<MechanicDto> execute() throws BusinessException {
		return Factory.repository.forMechanic().findById(id)
				.map(m -> DtoAssembler.toDto(m));
	}

}
