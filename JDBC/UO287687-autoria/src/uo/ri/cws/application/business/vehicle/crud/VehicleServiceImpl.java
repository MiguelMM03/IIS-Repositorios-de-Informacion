package uo.ri.cws.application.business.vehicle.crud;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.CommandExecutor;
import uo.ri.cws.application.business.vehicle.VehicleService;
import uo.ri.cws.application.business.vehicle.crud.commands.*;

public class VehicleServiceImpl implements VehicleService {

	CommandExecutor c = new CommandExecutor();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<VehicleBLDto> findVehicleByPlate(String plate) throws BusinessException {
		return c.execute(new FindVehicleByPlate(plate));
	}
}
