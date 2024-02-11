package uo.ri.cws.ui.foreman.reception.actions;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class ViewWorkOrderDetailAction implements Action {

	@Override
	public void execute() throws BusinessException {
	
		String woId = Console.readString("Work order id");
		
		WorkOrderService as = Factory.service.forWorkOrderService();
		Optional<WorkOrderDto> oWo = as.findWorkOrderById(woId);
		
		if ( oWo.isPresent() ) {
			Printer.printWorkOrderDetail( oWo.get() );
		} else {
			Console.println("There is no work order with that id");
		}

	}
}
