package uo.ri.cws.application.ui.manager.training.reports.actions;

import java.util.List;

import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.course.CourseService.TrainingForMechanicRow;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class ListTrainingOfMechanicAction implements Action {

	private ReportsUserInteractor user = new ReportsUserInteractor();

	@Override
	public void execute() throws Exception {
		String mId = user.askForMechanicId();

		List<TrainingForMechanicRow> rows = BusinessFactory.forCourseService().findTrainingHoursForMechanic(mId);

		Console.println("Training for mechanic report");
		printTotals(rows);

		Console.println("\n  - Breakdown by vehicle type -");
		rows.forEach((row) -> Printer.printTrainingForMechanic(row));
	}

	private void printTotals(List<TrainingForMechanicRow> rows) {
		int totalEnrolledHours = 0;
		int totalAttendedHours = 0;
		for (TrainingForMechanicRow r : rows) {
			totalEnrolledHours += r.enrolledHours;
			totalAttendedHours += r.attendedHours;
		}

		Console.printf("\tTotal enrolled hours: %d\n", totalEnrolledHours);
		Console.printf("\tTotal attended hours: %d\n", totalAttendedHours);
	}

}
