package uo.ri.cws.application.service.vehicletype.impl;

import java.util.List;
import java.util.stream.Collectors;

import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService.VehicleTypeDto;
import uo.ri.cws.domain.VehicleType;

public class DtoAssembler {

	public static VehicleTypeDto toDto(VehicleType vt) {
		VehicleTypeDto dto = new VehicleTypeDto();

		dto.id = vt.getId();
		dto.version = vt.getVersion();

		dto.name = vt.getName();
		dto.pricePerHour = vt.getPricePerHour();
		dto.minTrainigHours = vt.getMinTrainingHours();

		return dto;
	}

	public static List<VehicleTypeDto> toVehicleTypeDtoList(
			List<VehicleType> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

}
