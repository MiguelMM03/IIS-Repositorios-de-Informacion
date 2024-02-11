package uo.ri.cws.ui.manager.training.enrollment.actions;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService.EnrollmentDto;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class RegisterAttendanceAction implements Action {

	private AttendanceUserInteractor user = new AttendanceUserInteractor();

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		EnrollmentDto att = new EnrollmentDto();
		user.fill( att );

		// Invoke the service
		CourseEnrollmentService cs = Factory.service.forCourseEnrollmentService();
		cs.registerNew( att );

		// Show result
		Console.println("Attendance registered:" + att.id);
	}

}
