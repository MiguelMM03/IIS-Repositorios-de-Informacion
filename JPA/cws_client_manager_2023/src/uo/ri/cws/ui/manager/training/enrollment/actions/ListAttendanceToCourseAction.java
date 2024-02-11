package uo.ri.cws.ui.manager.training.enrollment.actions;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService.EnrollmentDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.menu.Action;

public class ListAttendanceToCourseAction implements Action {

	private AttendanceUserInteractor user = new AttendanceUserInteractor();
	
	@Override
	public void execute() throws Exception {
		String cId = user.askForCourseId();

		CourseEnrollmentService s = Factory.service.forCourseEnrollmentService();
		List<EnrollmentDto> attendance = s.findEnrollmentByCourseId( cId );

		attendance.forEach( att -> Printer.printAttendingMechanic(att) );
	}

}
