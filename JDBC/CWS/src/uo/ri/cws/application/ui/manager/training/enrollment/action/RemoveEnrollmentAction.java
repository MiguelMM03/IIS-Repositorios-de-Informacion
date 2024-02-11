package uo.ri.cws.application.ui.manager.training.enrollment.action;

import uo.ri.cws.application.business.BusinessFactory;
import util.console.Console;
import util.menu.Action;

public class RemoveEnrollmentAction implements Action {

	@Override
	public void execute() throws Exception {
		// Ask the user for data
		String attId = Console.readString("Attendance id");

		BusinessFactory.forEnrollmentService().deleteEnrollment(attId);
		// Show result
		Console.println("Course attendance removed");
	}

}
