package uo.ri.cws.application.business.enrollment.assembler;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.enrollment.EnrollmentService.EnrollmentBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.course.assembler.CourseAssembler;
import uo.ri.cws.application.persistence.enrollment.EnrollmentGateway.EnrollmentDALDto;

public class EnrollmentAssembler {
	public static EnrollmentBLDto DALDtoEnrollmentDto(EnrollmentDALDto m) {
		EnrollmentBLDto dto = new EnrollmentBLDto();
		dto.id = m.id;
		dto.version = m.version;
		dto.attendance = m.attendance;
		dto.passed = m.passed;
		dto.courseId = m.courseId;
		dto.mechanicId = m.mechanicId;
		dto.mechanic = MechanicAssembler.toMechanicDto(m.mechanic);
		dto.course = CourseAssembler.DALDtoCourseDto(m.course);
		return dto;
	}

	public static EnrollmentDALDto BLDtoEnrollmentDALDto(EnrollmentBLDto m) {
		EnrollmentDALDto result = new EnrollmentDALDto();
		result.id = m.id;
		result.version = m.version;
		result.attendance = m.attendance;
		result.passed = m.passed;
		result.courseId = m.courseId;
		result.mechanicId = m.mechanicId;
		result.mechanic = MechanicAssembler.toDALDto(m.mechanic);
		result.course = CourseAssembler.BLDtoCourseDALDto(m.course);
		return result;
	}

	public static List<EnrollmentBLDto> toEnrollmentDtoList(List<EnrollmentDALDto> listDALD) {
		List<EnrollmentBLDto> list = new ArrayList<EnrollmentBLDto>();
		for (EnrollmentDALDto c : listDALD) {
			list.add(DALDtoEnrollmentDto(c));
		}
		return list;
	}
}
