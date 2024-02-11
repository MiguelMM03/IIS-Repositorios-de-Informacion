package uo.ri.cws.ui.foreman.reception.actions;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class ListUnfinishedWorkOrdersAction implements Action {

	@Override
	public void execute() throws BusinessException {

		WorkOrderService as = Factory.service.forWorkOrderService();
		List<WorkOrderDto> wos = as.findUnfinishedWorkOrders();

		Console.println("In process work orders");
		for(WorkOrderDto wo: wos) {
			Printer.printWorkOrderDetail( wo );
		}

	}
}
