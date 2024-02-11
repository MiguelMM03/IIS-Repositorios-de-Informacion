package uo.ri.cws.application.persistence.course.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.course.CourseGateway.CourseDALDto;

public class CourseAssembler {

	public static CourseDALDto toCourseDALDto(ResultSet m) throws SQLException {
		CourseDALDto c = new CourseDALDto();
		c.id = m.getString("id");
		c.version = m.getLong("version");
		c.code =  m.getString("code");
		c.description =m.getString("description");
		c.startDate = m.getDate("startdate").toLocalDate();
		c.endDate = m.getDate("enddate").toLocalDate();
		c.hours = m.getInt("hours");
		c.name = m.getString("name");
		return c;
	}
	public static Optional<CourseDALDto> toOptionalCourseDALDto(ResultSet m) throws SQLException {
		if (m.next()) {
			return Optional.of(toCourseDALDto(m));
		} else
			return Optional.ofNullable(null);
	}
	public static List<CourseDALDto> toCourseDALDtoList(ResultSet rs)
			throws SQLException {
		List<CourseDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(toCourseDALDto(rs));
		}

		return res;
	}
}
