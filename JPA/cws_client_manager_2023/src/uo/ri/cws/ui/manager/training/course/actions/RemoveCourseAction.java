package uo.ri.cws.ui.manager.training.course.actions;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.course.CourseCrudService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class RemoveCourseAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		String cId = Console.readString("Course id");

		// Invoke the service
		CourseCrudService cs = Factory.service.forCourseCrudService();
		cs.deleteCourse( cId );

		// Show result
		Console.println("Course removed");
	}

}
