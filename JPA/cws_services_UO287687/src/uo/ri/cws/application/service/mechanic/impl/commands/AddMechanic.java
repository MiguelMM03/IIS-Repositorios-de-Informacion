package uo.ri.cws.application.service.mechanic.impl.commands;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

public class AddMechanic implements Command<MechanicDto> {

	private MechanicDto dto;
	private MechanicRepository mr = Factory.repository.forMechanic();

	public AddMechanic(MechanicDto dto) {
		ArgumentChecks.isNotNull(dto, "Mechanic can't be null");
		ArgumentChecks.isNotBlank(dto.dni, "Mechanic dni can`t be empty");
		this.dto = dto;
	}

	@Override
	public MechanicDto execute() throws BusinessException {
		BusinessChecks.isTrue(mr.findByDni(dto.dni).isEmpty(),
				"Another mechanic has the same dni");
		Mechanic m = new Mechanic(dto.dni, dto.surname, dto.name);
		mr.add(m);
		dto.id = m.getId();
		return dto;
	}

}
