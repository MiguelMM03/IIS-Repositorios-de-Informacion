package uo.ri.cws.ui.manager.training.reports.actions;

import java.util.Comparator;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.course.CourseCrudService;
import uo.ri.cws.application.service.course.CourseCrudService.TrainingForVehicleTypeReportLineDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class ListTrainingByVehicleTypeAction implements Action {

	@Override
	public void execute() throws Exception {

		CourseCrudService rs = Factory.service.forCourseCrudService();
		List<TrainingForVehicleTypeReportLineDto> rows = 
				rs.findTrainingSortedByVehicleTypeAndMechanic();

		Console.println("Training by vehicle type");
		rows.sort( new TVTRComparator() );
		rows.forEach( r ->
			Printer.printTrainingHoursRow( r )
		);
	}

	/**
	 * The sorting can be done in the query, but is also frequently done
	 * at the presentation layer
	 */
	private class TVTRComparator 
	implements Comparator<TrainingForVehicleTypeReportLineDto> {

		@Override
		public int compare(TrainingForVehicleTypeReportLineDto a,
				TrainingForVehicleTypeReportLineDto b) {

			int res = a.vehicleTypeName.compareTo( b.vehicleTypeName );
			if ( res == 0)  {
				res = a.mechanicFullName.compareTo( b.mechanicFullName);
			}
			return res;
		}

	}

}
