package uo.ri.cws.ui.manager.training.enrollment.actions;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.course.CourseCrudService;
import uo.ri.cws.application.service.course.CourseCrudService.CourseDto;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService.EnrollmentDto;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;

public class AttendanceUserInteractor {

	public void fill(EnrollmentDto att) throws BusinessException {
		fillCourseId(att);
		fillMechanicId(att);
		fillAttendance(att);
		fillPassed( att);
	}

	private void fillAttendance(EnrollmentDto att) {
		att.attendance = Console.readInt("% of attendance");
	}

	private void fillMechanicId(EnrollmentDto att) throws BusinessException {
		showMechanics();
		att.mechanicId = Console.readString("Mechanic id");
	}

	private void fillCourseId(EnrollmentDto att) throws BusinessException {
		showCourses();
		att.courseId = Console.readString("Course id");
	}

	private void fillPassed(EnrollmentDto att) {
		att.passed = Console.readString("Passed (y/n)?").equalsIgnoreCase("y");
	}

	private void showMechanics() throws BusinessException {
		MechanicCrudService cs = Factory.service.forMechanicCrudService();
		List<MechanicDto> mechanics = cs.findAllMechanics();
		Console.println("List of mechanics");
		mechanics.forEach((m) -> Printer.printMechanic(m) );
	}

	public void showCourses() throws BusinessException {
		CourseCrudService cs = Factory.service.forCourseCrudService();
		List<CourseDto> courses = cs.findAllActiveCourses();
		Console.println("List of courses");
		courses.forEach((c) -> Printer.printCourse(c) );
	}

	public String askForCourseId() throws BusinessException {
		showCourses();
		return Console.readString("Course id");
	}

}
