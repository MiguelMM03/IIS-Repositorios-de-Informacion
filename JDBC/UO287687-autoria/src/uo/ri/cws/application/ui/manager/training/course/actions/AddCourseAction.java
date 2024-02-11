package uo.ri.cws.application.ui.manager.training.course.actions;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import util.console.Console;
import util.menu.Action;

public class AddCourseAction implements Action {

	private CourseUserInteractor user = new CourseUserInteractor();

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		CourseBLDto c = new CourseBLDto();
		user.fill(c);

		BusinessFactory.forCourseService().registerNew(c);

		// Show result
		Console.println("New course registered: " + c.id);
	}

}
