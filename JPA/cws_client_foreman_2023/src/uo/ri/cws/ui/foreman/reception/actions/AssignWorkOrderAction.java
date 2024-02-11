package uo.ri.cws.ui.foreman.reception.actions;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class AssignWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String woId = Console.readString("Work order id");
		String mId = Console.readString("Mechanic id");

		WorkOrderService as = Factory.service.forWorkOrderService();
		as.assignWorkOrderToMechanic(woId, mId);

		Console.println("\nAssignation done");
	}
}
