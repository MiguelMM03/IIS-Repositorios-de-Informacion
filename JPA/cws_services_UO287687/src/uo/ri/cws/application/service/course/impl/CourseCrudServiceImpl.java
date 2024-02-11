package uo.ri.cws.application.service.course.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.course.CourseCrudService;
import uo.ri.cws.application.service.course.impl.commands.FindTrainingSortedByVehicleTypeAndMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseCrudServiceImpl implements CourseCrudService{

	private CommandExecutor executor =Factory.executor.forExecutor();
	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCourse(String id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseDto> findAllActiveCourses() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CourseDto> findCourseById(String cId)
			throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<TrainingForMechanicReportLineDto> findTrainingHoursByMechanicId(
			String mechanicId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrainingForVehicleTypeReportLineDto> findTrainingSortedByVehicleTypeAndMechanic()
			throws BusinessException {
		return executor.execute(new FindTrainingSortedByVehicleTypeAndMechanic());
	}

}
