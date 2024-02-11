package uo.ri.cws.ui.manager.training.reports.actions;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService.VehicleTypeDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;

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
		VehicleTypeCrudService cs = Factory.service.forVehicleTypeCrudService();
		List<VehicleTypeDto> mechanics = cs.findAllVehicleTypes();
		Console.println("List of vehicle types");
		mechanics.forEach((vt) -> Printer.printVehicleType( vt ) );
	}

	private void showMechanics() throws BusinessException {
		MechanicCrudService cs = Factory.service.forMechanicCrudService();
		List<MechanicDto> mechanics = cs.findAllMechanics();
		Console.println("List of mechanics");
		mechanics.forEach((m) -> Printer.printMechanic(m) );
	}

}
