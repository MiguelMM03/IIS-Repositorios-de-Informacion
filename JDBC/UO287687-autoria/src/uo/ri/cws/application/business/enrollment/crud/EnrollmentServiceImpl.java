package uo.ri.cws.application.business.enrollment.crud;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.enrollment.EnrollmentService;
import util.exception.NotYetImplementedException;

public class EnrollmentServiceImpl implements EnrollmentService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnrollmentBLDto registerNew(EnrollmentBLDto dto) throws BusinessException {
		throw new NotYetImplementedException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEnrollment(String id) throws BusinessException {
		throw new NotYetImplementedException();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EnrollmentBLDto> findEnrollmentByCourseId(String id) throws BusinessException {
		throw new NotYetImplementedException();
	}

}
