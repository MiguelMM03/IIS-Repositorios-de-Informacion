package uo.ri.cws.application.ui.manager.training.enrollment.action;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.enrollment.EnrollmentService.EnrollmentBLDto;
import util.console.Console;
import util.menu.Action;

public class RegisterEnrollmentAction implements Action {

	private EnrollmentUserInteractor user = new EnrollmentUserInteractor();

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		EnrollmentBLDto att = new EnrollmentBLDto();
		user.fill(att);

		BusinessFactory.forEnrollmentService().registerNew(att);
		Console.println("Attendance registered:" + att.id);
	}

}
