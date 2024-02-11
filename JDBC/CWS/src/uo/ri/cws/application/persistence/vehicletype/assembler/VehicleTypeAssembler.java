package uo.ri.cws.application.persistence.vehicletype.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway.VehicleTypeDALDto;


public class VehicleTypeAssembler {
	
	public static VehicleTypeDALDto resultSetToVehicleTypeDALDto(ResultSet rs)
		    throws SQLException {
		VehicleTypeDALDto value = new VehicleTypeDALDto();
		value.id = rs.getString("id");
		value.version = rs.getLong("version");

		value.minTrainigHours = rs.getInt("minTrainingHours");
		value.name = rs.getString("name");
		value.pricePerHour = rs.getDouble("pricePerHour");
		return value;
	    }
	public static Optional<VehicleTypeDALDto> toVehicleTypeDALDto(ResultSet rs)
		    throws SQLException {
		VehicleTypeDALDto result = null;
		if (rs.next())
		    result = resultSetToVehicleTypeDALDto(rs);

		return Optional.ofNullable(result);
	    }
	public static List<VehicleTypeDALDto> toVehicleTypeDALDtoList(ResultSet rs)
			throws SQLException {
		List<VehicleTypeDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToVehicleTypeDALDto(rs));
		}

		return res;
	}
}
