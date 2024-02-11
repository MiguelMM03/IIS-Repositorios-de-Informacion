package uo.ri.cws.ui.manager.training.enrollment.actions;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class RemoveAttendanceAction implements Action {

	@Override
	public void execute() throws Exception {
		// Ask the user for data
		String attId = Console.readString("Attendance id");

		// Invoke the service
		CourseEnrollmentService cs = Factory.service.forCourseEnrollmentService();
		cs.deleteEnrollment( attId );

		// Show result
		Console.println("Course attendance removed");
	}

}
