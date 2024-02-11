package uo.ri.cws.application.ui.foreman.reception.action;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import util.console.Console;
import util.menu.Action;

public class RemoveWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String woId = Console.readString("Work order id");

		BusinessFactory.forWorkOrderService().deleteWorkOrder(woId);

		Console.println("\nThe work order has been deleted");
	}
}
