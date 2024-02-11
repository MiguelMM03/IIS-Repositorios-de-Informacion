package uo.ri.cws.application.persistence.intervention.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.persistence.intervention.InterventionGateway.InterventionDALDto;

public class InterventionAssembler {

	public static List<InterventionDALDto> toInterventionDALDtoList(ResultSet rs)
			throws SQLException {
		List<InterventionDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToInterventionDALDto(rs));
		}

		return res;
	}
	private static InterventionDALDto resultSetToInterventionDALDto(ResultSet rs)
			throws SQLException {
		InterventionDALDto value = new InterventionDALDto();
		value.id = rs.getString("id");
		value.version = rs.getLong("version");

		value.date = rs.getTimestamp("date").toLocalDateTime();
		value.minutes = rs.getInt("minutes");
		value.mechanicId = rs.getString("mechanic_id");
		value.workorderId = rs.getString("workorder_id");
		return value;
	}
}
