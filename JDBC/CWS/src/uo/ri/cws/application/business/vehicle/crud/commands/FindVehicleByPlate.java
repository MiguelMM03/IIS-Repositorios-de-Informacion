package uo.ri.cws.application.business.vehicle.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.vehicle.assembler.VehicleAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import util.assertion.Argument;

public class FindVehicleByPlate implements Command<Optional<VehicleBLDto>> {

	private VehicleGateway vg = PersistenceFactory.forVehicle();
	private String plate;

	public FindVehicleByPlate(String plate) {
		Argument.isNotEmpty(plate);
		this.plate = plate;
	}

	@Override
	public Optional<VehicleBLDto> execute() throws BusinessException {
		return VehicleAssembler.toVehicleDto(vg.findByPlate(plate));
	}

}
