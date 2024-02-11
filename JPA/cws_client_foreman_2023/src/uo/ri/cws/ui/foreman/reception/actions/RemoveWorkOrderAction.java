package uo.ri.cws.ui.foreman.reception.actions;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class RemoveWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {
	
		String woId = Console.readString("Work order id");
		
		WorkOrderService as = Factory.service.forWorkOrderService();
		as.deleteWorkOrder( woId );
		
		Console.println("\nThe work order has been deleted");  
	}
}
