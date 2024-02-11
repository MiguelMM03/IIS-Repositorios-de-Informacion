package uo.ri.cws.application.service.enrollment.impl;

import java.util.List;
import java.util.stream.Collectors;

import uo.ri.cws.application.service.enrollment.CourseEnrollmentService.CourseForEnrollmentDto;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService.EnrollmentDto;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService.MechanicForEnrollmentDto;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;

public class DtoAssembler {

	public static MechanicForEnrollmentDto toDto(Mechanic m) {
		MechanicForEnrollmentDto dto = new MechanicForEnrollmentDto();
		dto.id = m.getId();
		dto.version = m.getVersion();

		dto.dni = m.getDni();
		dto.name = m.getName();
		dto.surname = m.getSurname();
		return dto;
	}

	public static CourseForEnrollmentDto toDto(Course c) {
		CourseForEnrollmentDto dto = new CourseForEnrollmentDto();

		dto.id = c.getId();
		dto.version = c.getVersion();

		dto.code = c.getCode();
		dto.name = c.getName();
		dto.description = c.getDescription();
		dto.startDate = c.getStartDate();
		dto.endDate = c.getEndDate();

		for (Dedication d: c.getDedications()) {
			dto.percentages.put( d.getVehicleType().getId(), d.getPercentage() );
		}

		return dto;
	}

	public static List<CourseForEnrollmentDto> toCourseDtoList(
			List<Course> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

	public static List<EnrollmentDto> toEnrollmentDtoList(
			List<Enrollment> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

	public static EnrollmentDto toDto(Enrollment e) {
		EnrollmentDto dto = new EnrollmentDto();

		dto.id = e.getId();
		dto.version = e.getVersion();

		dto.courseId = e.getCourse().getId();
		dto.mechanicId = e.getMechanic().getId();
		dto.attendance = e.getAttendance();
		dto.passed = e.isPassed();

		dto.mechanic = toDto( e.getMechanic() );
		dto.course = toDto( e.getCourse() );

		return dto;
	}

}
