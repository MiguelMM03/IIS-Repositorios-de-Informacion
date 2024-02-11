package uo.ri.cws.ui.manager.training.course.actions;

import java.util.List;
import java.util.Map;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.course.CourseCrudService.CourseDto;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService.VehicleTypeDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;

/**
 * Asks the user for basic data about a course through the console
 */
public class CourseUserInteractor {

	public void fill(CourseDto c) throws BusinessException {
		c.code = Console.readString("Code");
		c.name = Console.readString("Name");
		c.description = Console.readString("Description");
		c.startDate = Console.readDate("Start date");
		c.endDate = Console.readDate("End date");
		c.hours = Console.readInt("Duration in hours");

		showAllVehicleTypes();
		askDedicationPercentages(c.percentages);
	}

	private void askDedicationPercentages(Map<String, Integer> percentages)
			throws BusinessException {
		percentages.clear();
		int total = 0;
		while ( total < 100 ) {
			String vtId = Console.readString("Vehicle type Id");
			Integer percentage = Console.readInt("Percentage");
			percentages.put(vtId, percentage);

			total += percentage;
		}
	}

	private void showAllVehicleTypes() throws BusinessException {
		VehicleTypeCrudService cs = Factory.service.forVehicleTypeCrudService();

		List<VehicleTypeDto> vts = cs.findAllVehicleTypes();
		Console.print("Vehicle types");
		for(VehicleTypeDto vt: vts) {
			Printer.printVehicleType( vt );
		}
	}

}
