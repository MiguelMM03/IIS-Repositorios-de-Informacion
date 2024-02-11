package uo.ri.cws.application.ui.manager.training.enrollment.action;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.course.CourseService;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import uo.ri.cws.application.business.enrollment.EnrollmentService.EnrollmentBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;

public class EnrollmentUserInteractor {
	public void fill(EnrollmentBLDto att) throws BusinessException {
		fillCourseId(att);
		fillMechanicId(att);
		fillAttendance(att);
		fillPassed(att);
	}

	private void fillAttendance(EnrollmentBLDto att) {
		att.attendance = Console.readInt("% of attendance");
	}

	private void fillMechanicId(EnrollmentBLDto att) throws BusinessException {
		showMechanics();
		att.mechanicId = Console.readString("Mechanic id");
	}

	private void fillCourseId(EnrollmentBLDto att) throws BusinessException {
		showCourses();
		att.courseId = Console.readString("Course id");
	}

	private void fillPassed(EnrollmentBLDto att) {
		att.passed = Console.readString("Passed (y/n)?").equalsIgnoreCase("y");
	}

	private void showMechanics() throws BusinessException {
		MechanicService cs = BusinessFactory.forMechanicService();
		List<MechanicBLDto> mechanics = cs.findAllMechanics();
		Console.println("List of mechanics");
		mechanics.forEach((m) -> Printer.printMechanic(m));
	}

	public void showCourses() throws BusinessException {
		CourseService cs = BusinessFactory.forCourseService();
		List<CourseBLDto> mechanics = cs.findAllCourses();
		Console.println("List of courses");
		mechanics.forEach((c) -> Printer.printCourse(c));
	}

	public String askForCourseId() throws BusinessException {
		showCourses();
		return Console.readString("Course id");
	}

}
