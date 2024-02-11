package uo.ri.cws.application.ui.manager.training.course.actions;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import util.console.Console;
import util.menu.Action;

public class UpdateCourseAction implements Action {

	private CourseUserInteractor user = new CourseUserInteractor();

	@Override
	public void execute() throws BusinessException {

		String cId = Console.readString("Course id");
		Optional<CourseBLDto> c = BusinessFactory.forCourseService().findCourseById(cId);
		if (c.isEmpty())
			throw new BusinessException("Course does not exist");

		user.fill(c.get());

		BusinessFactory.forCourseService().updateCourse(c.get());

		Console.println("Course updated");
	}

}
