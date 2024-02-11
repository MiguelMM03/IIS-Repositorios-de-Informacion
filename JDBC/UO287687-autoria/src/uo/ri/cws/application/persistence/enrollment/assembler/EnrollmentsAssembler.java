package uo.ri.cws.application.persistence.enrollment.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.persistence.enrollment.EnrollmentGateway.EnrollmentDALDto;

public class EnrollmentsAssembler {
	public static EnrollmentDALDto toEnrollmentDALDto(ResultSet m) throws SQLException {
		EnrollmentDALDto c = new EnrollmentDALDto();
		c.id = m.getString("id");
		c.version = m.getLong("version");
		c.mechanicId =  m.getString("mechanic_id");
		c.courseId = m.getString("course_id");
		c.attendance =m.getInt("attendance");
		c.passed = m.getBoolean("passed");
		return c;
	}
	public static List<EnrollmentDALDto> toCertificateDALDtoList(ResultSet rs)
			throws SQLException {
		List<EnrollmentDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(toEnrollmentDALDto(rs));
		}

		return res;
	}
}
