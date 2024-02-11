package uo.ri.cws.application.service.enrollment.impl;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService;
import uo.ri.util.exception.NotYetImplementedException;

public class CourseEnrollmentServiceImpl implements CourseEnrollmentService{

	@Override
	public EnrollmentDto registerNew(EnrollmentDto dto)
			throws BusinessException {
		throw new NotYetImplementedException();
	}

	@Override
	public void deleteEnrollment(String id) throws BusinessException {
		throw new NotYetImplementedException();
		
	}

	@Override
	public List<EnrollmentDto> findEnrollmentByCourseId(String id)
			throws BusinessException {
		throw new NotYetImplementedException();
	}

}
