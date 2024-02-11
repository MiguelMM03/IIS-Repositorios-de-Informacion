package uo.ri.cws.application.service.course.impl;

import java.util.List;
import java.util.stream.Collectors;

import uo.ri.cws.application.service.course.CourseCrudService.CourseDto;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;

public class DtoAssembler {

	public static CourseDto toDto(Course c) {
		CourseDto dto = new CourseDto();

		dto.id = c.getId();
		dto.version = c.getVersion();

		dto.code = c.getCode();
		dto.name = c.getName();
		dto.description = c.getDescription();
		dto.startDate = c.getStartDate();
		dto.endDate = c.getEndDate();
		dto.hours = c.getHours();

		for (Dedication d: c.getDedications()) {
			dto.percentages.put( d.getVehicleType().getId(), d.getPercentage() );
		}

		return dto;
	}

	public static List<CourseDto> toCourseDtoList(
			List<Course> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

}
