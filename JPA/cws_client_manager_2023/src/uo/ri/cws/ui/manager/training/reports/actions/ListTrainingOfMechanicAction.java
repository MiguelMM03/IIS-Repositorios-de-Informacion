package uo.ri.cws.ui.manager.training.reports.actions;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.course.CourseCrudService;
import uo.ri.cws.application.service.course.CourseCrudService.TrainingForMechanicReportLineDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class ListTrainingOfMechanicAction implements Action {

	private ReportsUserInteractor user = new ReportsUserInteractor();
	
	@Override
	public void execute() throws Exception {
		String mId = user.askForMechanicId();

		CourseCrudService rs = Factory.service.forCourseCrudService();
		List<TrainingForMechanicReportLineDto> rows = 
				rs.findTrainingHoursByMechanicId(mId);

		Console.println("Training for mechanic report");
		printTotals( rows );

		Console.println("\n  - Breakdown by vehicle type -");
		rows.forEach( (row) ->
			Printer.printTrainingForMechanic( row )
		);
	}

	private void printTotals(List<TrainingForMechanicReportLineDto> rows) {
		int totalEnrolledHours = 0;
		int totalAttendedHours = 0;
		for(TrainingForMechanicReportLineDto r: rows) {
			totalEnrolledHours += r.enrolledHours;
			totalAttendedHours += r.attendedHours;
		}

		Console.printf("\tTotal enrolled hours: %d\n", totalEnrolledHours);
		Console.printf("\tTotal attended hours: %d\n", totalAttendedHours);
	}

}
