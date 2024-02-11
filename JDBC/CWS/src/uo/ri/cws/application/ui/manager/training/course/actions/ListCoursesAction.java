package uo.ri.cws.application.ui.manager.training.course.actions;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class ListCoursesAction implements Action {

	@Override
	public void execute() throws BusinessException {
		List<CourseBLDto> courses = BusinessFactory.forCourseService().findAllCourses();

		Console.println("\nList of courses\n");
		for (CourseBLDto c : courses) {
			Printer.printCourse(c);
		}

	}
}
