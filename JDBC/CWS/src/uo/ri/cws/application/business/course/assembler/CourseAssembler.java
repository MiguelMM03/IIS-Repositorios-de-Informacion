package uo.ri.cws.application.business.course.assembler;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import uo.ri.cws.application.persistence.course.CourseGateway.CourseDALDto;

public class CourseAssembler {
	public static CourseBLDto DALDtoCourseDto(CourseDALDto m){
		CourseBLDto dto = new CourseBLDto();
		dto.id = m.id;
		dto.version = m.version;
		dto.code=m.code;
		dto.description=m.description;
		dto.endDate=m.endDate;
		dto.hours=m.hours;
		dto.name=m.name;
		dto.startDate=m.startDate;
		return dto;
	}
	public static CourseDALDto BLDtoCourseDALDto(CourseBLDto m){
		CourseDALDto result = new CourseDALDto();
		result.id = m.id;
		result.version = m.version;
		result.code=m.code;
		result.description=m.description;
		result.endDate=m.endDate;
		result.hours=m.hours;
		result.name=m.name;
		result.startDate=m.startDate;
		return result;
	}
	public static List<CourseBLDto> toCourseDtoList(List<CourseDALDto> listDALD){
		List<CourseBLDto> list=new ArrayList<CourseBLDto>();
		for(CourseDALDto c: listDALD) {
			list.add(DALDtoCourseDto(c));
		}
		return list;
	}
}
