package uo.ri.cws.application.ui.manager.training.reports.actions;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.course.CourseService.TrainingHoursRow;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class ListTrainingByVehicleTypeAction implements Action {

	@Override
	public void execute() throws Exception {

		List<TrainingHoursRow> rows = BusinessFactory.forCourseService().findAllTrainingHours();

		Console.println("Training by vehicle type");
		rows.sort(new TVTRComparator());
		String lastVehicleType=null;
		for (Iterator<TrainingHoursRow> iterator = rows.iterator(); iterator.hasNext();) {
			TrainingHoursRow trainingHoursRow = iterator.next();
			if(lastVehicleType==null || !lastVehicleType.equals(trainingHoursRow.vehicleTypeName)) {
				lastVehicleType=trainingHoursRow.vehicleTypeName;
				Console.println(lastVehicleType);
				Printer.printTrainingHoursRow(trainingHoursRow);
			}
			else {
				Printer.printTrainingHoursRow(trainingHoursRow);
			}
			
		}
	}

	/**
	 * The sorting can be done in the query, but is also frequently done at the
	 * presentation layer
	 */
	private class TVTRComparator implements Comparator<TrainingHoursRow> {

		@Override
		public int compare(TrainingHoursRow a, TrainingHoursRow b) {

			int res = a.vehicleTypeName.compareTo(b.vehicleTypeName);
			if (res == 0) {
				res = a.mechanicFullName.compareTo(b.mechanicFullName);
			}
			return res;
		}

	}

}
