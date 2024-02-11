package uo.ri.cws.application.ui.manager.training.enrollment.action;

import java.util.List;

import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.enrollment.EnrollmentService.EnrollmentBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.menu.Action;

public class ListEnrollmentToCourseAction implements Action {

	private EnrollmentUserInteractor user = new EnrollmentUserInteractor();

	@Override
	public void execute() throws Exception {
		String cId = user.askForCourseId();

		List<EnrollmentBLDto> attendance = BusinessFactory.forEnrollmentService().findEnrollmentByCourseId(cId);

		attendance.forEach(att -> Printer.printAttendingMechanic(att));
	}

}
