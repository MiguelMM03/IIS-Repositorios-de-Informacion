package uo.ri.cws.application.ui.foreman.reception.action;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class RegisterWorkOrderAction implements Action {

	private WorkOrderUserInteractor user = new WorkOrderUserInteractor();

	@Override
	public void execute() throws BusinessException {

		VehicleBLDto v = user.askForVehicle();
		Printer.printVehicleDetail(v);

		WorkOrderBLDto wo = user.askForWorkOrder(v);
		BusinessFactory.forWorkOrderService().registerNew(wo);

		Console.println("\nWork order registered: " + wo.id);
	}

}
