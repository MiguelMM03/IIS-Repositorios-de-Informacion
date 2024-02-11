package uo.ri.cws.application.business.vehicletype.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway.VehicleTypeDALDto;

public class VehicleTypeAssembler {

	public static VehicleTypeBLDto vehicleTypeDALDtoToBLDto(VehicleTypeDALDto v) {
		VehicleTypeBLDto value = new VehicleTypeBLDto();
		value.id = v.id;
		value.version = v.version;

		value.minTrainigHours = v.minTrainigHours;
		value.name = v.name;
		value.pricePerHour = v.pricePerHour;
		return value;
	}

	public static VehicleTypeBLDto resultSetToVehicleTypeBLDto(ResultSet rs) throws SQLException {
		VehicleTypeBLDto value = new VehicleTypeBLDto();
		value.id = rs.getString("id");
		value.version = rs.getLong("version");

		value.minTrainigHours = rs.getInt("minTrainigHours");
		value.name = rs.getString("name");
		value.pricePerHour = rs.getDouble("pricePerHour");
		return value;
	}

	public static List<VehicleTypeBLDto> toVehicleTypeDtoList(List<VehicleTypeDALDto> listDALD) {
		List<VehicleTypeBLDto> list = new ArrayList<VehicleTypeBLDto>();
		for (VehicleTypeDALDto c : listDALD) {
			list.add(vehicleTypeDALDtoToBLDto(c));
		}
		return list;
	}
}
