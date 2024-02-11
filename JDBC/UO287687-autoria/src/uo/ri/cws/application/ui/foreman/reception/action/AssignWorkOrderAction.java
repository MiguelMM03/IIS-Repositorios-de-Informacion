package uo.ri.cws.application.ui.foreman.reception.action;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class AssignWorkOrderAction implements Action {
	private WorkOrderUserInteractor user = new WorkOrderUserInteractor();

	@Override
	public void execute() throws BusinessException {

		VehicleBLDto v = user.askForVehicle();
		List<WorkOrderBLDto> wos = BusinessFactory.forWorkOrderService().findWorkOrdersByPlateNumber(v.plateNumber);
		if (wos.isEmpty())
			throw new BusinessException("The vehicle has not any work order");

		Printer.printWorkOrders(wos);
		String woId = Console.readString("Work order ID");

		Printer.printMechanics(BusinessFactory.forMechanicService().findAllMechanics());
		String mechanicDNI = Console.readString("Mechanic DNI");

		BusinessFactory.forWorkOrderService().assignWorkOrderToMechanic(woId, mechanicDNI);
		Console.println("\nAssignation done");
	}
}
