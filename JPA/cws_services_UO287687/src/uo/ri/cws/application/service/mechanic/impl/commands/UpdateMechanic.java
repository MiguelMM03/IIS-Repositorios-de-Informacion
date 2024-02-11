package uo.ri.cws.application.service.mechanic.impl.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

public class UpdateMechanic implements Command<Void>{

	private MechanicDto dto;

	public UpdateMechanic(MechanicDto dto) {
		ArgumentChecks.isNotNull(dto, "Mechanic can't be null");
		ArgumentChecks.isNotBlank(dto.id, "Id can't be empty");
		ArgumentChecks.isNotBlank(dto.dni, "Dni can't be empty");
		ArgumentChecks.isNotBlank(dto.name, "Name can't be empty");
		ArgumentChecks.isNotBlank(dto.surname, "Mechanic can't be empty");
		this.dto = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<Mechanic> om=Factory.repository.forMechanic().findById(dto.id);
		BusinessChecks.isTrue(om.isPresent(),"Mechanic doesn't exist");
		Mechanic m=om.get();
		BusinessChecks.hasVersion(m, dto.version);
		m.setName(dto.name);
		m.setSurname(dto.surname);
		return null;
	}

}
