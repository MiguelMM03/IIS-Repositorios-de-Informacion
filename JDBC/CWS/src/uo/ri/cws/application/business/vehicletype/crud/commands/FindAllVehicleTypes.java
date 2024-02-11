package uo.ri.cws.application.business.vehicletype.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.business.vehicletype.assembler.VehicleTypeAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindAllVehicleTypes implements Command<List<VehicleTypeBLDto>>{

	@Override
	public List<VehicleTypeBLDto> execute() throws BusinessException {
		return VehicleTypeAssembler.toVehicleTypeDtoList(PersistenceFactory.forVehicleType().findAll());
	}

}
