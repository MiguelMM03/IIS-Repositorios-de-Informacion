package uo.ri.cws.application.business.vehicle.assembler;

import java.util.Optional;

import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;

public class VehicleAssembler {
	public static VehicleBLDto toVehicleDto(VehicleDALDto arg) {
		VehicleBLDto result = new VehicleBLDto();
		result.id = arg.id;
		result.make = arg.make;
		result.model = arg.model;
		result.plateNumber = arg.plateNumber;
		result.version = arg.version;
		result.clientId = arg.clientId;
		result.vehicleTypeId = arg.vehicleTypeId;
		return result;
	}
	public static Optional<VehicleBLDto> toVehicleDto(Optional<VehicleDALDto> arg) {
		Optional<VehicleBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toVehicleDto(arg.get()));
		return result;
	}
}
