package uo.ri.cws.ui.manager.training.course.actions;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.course.CourseCrudService;
import uo.ri.cws.application.service.course.CourseCrudService.CourseDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class ListCoursesAction implements Action {

	@Override
	public void execute() throws BusinessException {
	
		CourseCrudService as = Factory.service.forCourseCrudService();
		List<CourseDto> courses = as.findAllCourses();
		
		Console.println("\nList of courses\n");  
		for(CourseDto c : courses) {
			Printer.printCourse( c );
		}

	}
}
