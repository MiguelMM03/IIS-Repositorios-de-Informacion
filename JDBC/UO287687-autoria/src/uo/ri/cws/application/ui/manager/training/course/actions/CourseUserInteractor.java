
package uo.ri.cws.application.ui.manager.training.course.actions;

import java.util.List;
import java.util.Map;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;

/**
 * Asks the user for basic data about a course through the console
 */
public class CourseUserInteractor {

	public void fill(CourseBLDto c) throws BusinessException {
		c.code = Console.readString("Code");
		c.name = Console.readString("Name");
		c.description = Console.readString("Description");
		c.startDate = Console.readDate("Start date");
		c.endDate = Console.readDate("End date");
		c.hours = Console.readInt("Duration in hours");

		showAllVehicleTypes();
		askDedicationPercentages(c.percentages);
	}

	private void askDedicationPercentages(Map<String, Integer> percentages) throws BusinessException {
		percentages.clear();
		int total = 0;
		while (total < 100) {
			String vtId = Console.readString("Vehicle type Id");
			Integer percentage = Console.readInt("Percentage");
			percentages.put(vtId, percentage);

			total += percentage;
		}
	}

	private void showAllVehicleTypes() throws BusinessException {
		VehicleTypeService cs = BusinessFactory.forVehicleTypeService();

		List<VehicleTypeBLDto> vts = cs.findAllVehicleTypes();
		Console.print("Vehicle types");
		for (VehicleTypeBLDto vt : vts) {
			Printer.printVehicleType(vt);
		}
	}

}
