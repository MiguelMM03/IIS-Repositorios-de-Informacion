package uo.ri.cws.application.ui.foreman.reception.action;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class ListUnfinishedWorkOrdersAction implements Action {

    @Override
    public void execute() throws BusinessException {

	List<WorkOrderBLDto> wos = BusinessFactory.forWorkOrderService().findUnfinishedWorkOrders();

	Console.println("In process work orders");
	for (WorkOrderBLDto wo : wos) {
	    Printer.printWorkOrderDetail(wo);
	}

    }
}
