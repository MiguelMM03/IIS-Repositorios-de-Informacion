package uo.ri.cws.application.ui.manager.training.reports.actions;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;

public class ReportsUserInteractor {

	public String askForMechanicId() throws BusinessException {
		showMechanics();
		return Console.readString("Mechanic id");
	}

	public String askForVehicleTypeId() throws BusinessException {
		showVehicleTypes();
		return Console.readString("Vehicle type id");
	}

	private void showVehicleTypes() throws BusinessException {
		VehicleTypeService cs = BusinessFactory.forVehicleTypeService();
		List<VehicleTypeBLDto> mechanics = cs.findAllVehicleTypes();
		Console.println("List of vehicle types");
		mechanics.forEach((vt) -> Printer.printVehicleType(vt));
	}

	private void showMechanics() throws BusinessException {
		MechanicService cs = BusinessFactory.forMechanicService();
		List<MechanicBLDto> mechanics = cs.findAllMechanics();
		Console.println("List of mechanics");
		mechanics.forEach((m) -> Printer.printMechanic(m));
	}

}
