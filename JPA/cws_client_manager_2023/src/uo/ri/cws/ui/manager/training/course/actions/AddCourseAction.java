package uo.ri.cws.ui.manager.training.course.actions;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.course.CourseCrudService;
import uo.ri.cws.application.service.course.CourseCrudService.CourseDto;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class AddCourseAction implements Action {

	private CourseUserInteractor user = new CourseUserInteractor();
	
	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		CourseDto c = new CourseDto();
		user.fill( c );

		// Invoke the service
		CourseCrudService cs = Factory.service.forCourseCrudService();
		cs.registerNew(c);

		// Show result
		Console.println("New course registered: " + c.id);
	}

}
