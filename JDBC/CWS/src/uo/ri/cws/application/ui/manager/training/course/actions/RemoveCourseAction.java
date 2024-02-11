package uo.ri.cws.application.ui.manager.training.course.actions;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import util.console.Console;
import util.menu.Action;

public class RemoveCourseAction implements Action {

    @Override
    public void execute() throws BusinessException {

	// Ask the user for data
	String cId = Console.readString("Course id");

	BusinessFactory.forCourseService().deleteCourse(cId);

	// Show result
	Console.println("Course removed");
    }

}
