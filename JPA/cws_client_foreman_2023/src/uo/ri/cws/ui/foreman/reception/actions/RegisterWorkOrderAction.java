package uo.ri.cws.ui.foreman.reception.actions;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.vehicle.VehicleCrudService.VehicleDto;
import uo.ri.cws.application.service.workorder.WorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class RegisterWorkOrderAction implements Action {

	private WorkOrderUserInteractor user = new WorkOrderUserInteractor();

	@Override
	public void execute() throws BusinessException {

		VehicleDto v = user.askForVehicle(); 
		Printer.printVehicleDetail( v );
		
		WorkOrderDto wo = user.askForWorkOrder(v);
		
		WorkOrderService as = Factory.service.forWorkOrderService();
		as.registerNew( wo );
		
		Console.println("\nWork order registered: " + wo.id);  
	}

}
