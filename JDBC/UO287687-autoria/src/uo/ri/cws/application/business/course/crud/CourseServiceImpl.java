package uo.ri.cws.application.business.course.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.course.CourseService;
import uo.ri.cws.application.business.course.crud.commands.FindAllTrainingHours;
import uo.ri.cws.application.business.course.crud.commands.FindTrainingHoursRowForMechanic;
import uo.ri.cws.application.business.util.command.CommandExecutor;
import util.exception.NotYetImplementedException;

public class CourseServiceImpl implements CourseService {

	CommandExecutor c = new CommandExecutor();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CourseBLDto registerNew(CourseBLDto dto) throws BusinessException {
		throw new NotYetImplementedException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateCourse(CourseBLDto dto) throws BusinessException {
		throw new NotYetImplementedException();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteCourse(String id) throws BusinessException {
		throw new NotYetImplementedException();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CourseBLDto> findAllCourses() throws BusinessException {
		throw new NotYetImplementedException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<CourseBLDto> findCourseById(String cId) throws BusinessException {
		throw new NotYetImplementedException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CourseBLDto> findAllActiveCourses() throws BusinessException {
		throw new NotYetImplementedException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TrainingForMechanicRow> findTrainingHoursForMechanic(String mechanicId) throws BusinessException {
		return c.execute(new FindTrainingHoursRowForMechanic(mechanicId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TrainingHoursRow> findAllTrainingHours() throws BusinessException {
		return c.execute(new FindAllTrainingHours());
	}

}
