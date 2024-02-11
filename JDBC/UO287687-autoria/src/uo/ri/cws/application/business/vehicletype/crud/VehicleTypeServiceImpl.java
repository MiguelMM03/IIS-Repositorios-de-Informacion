package uo.ri.cws.application.business.vehicletype.crud;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.CommandExecutor;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService;
import uo.ri.cws.application.business.vehicletype.crud.commands.FindAllVehicleTypes;

public class VehicleTypeServiceImpl implements VehicleTypeService {

	CommandExecutor c=new CommandExecutor();
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VehicleTypeBLDto> findAllVehicleTypes() throws BusinessException {
		return c.execute(new FindAllVehicleTypes());
	}

}
