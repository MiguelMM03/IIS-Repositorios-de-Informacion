package uo.ri.cws.ui.foreman.reception.actions;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class UpdateWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String woId = Console.readString("Work order id");
		
		WorkOrderService as = Factory.service.forWorkOrderService();
		Optional<WorkOrderDto> oWo = as.findWorkOrderById(woId);
		assertPresent(oWo);
		/*
		 * it can also be checked here the work order is in OPEN 
		 * or ASSIGNED status. It is checked by the service anyway, but doing 
		 * it here improves the user experience by detecting the problem earlier. 
		 */
		
		String description = Console.readString("New description");
		WorkOrderDto wo = oWo.get();
		wo.description = description;
		
		as.updateWorkOrder( wo );
		
		Console.println("\nUpdate done");  
	}

	private void assertPresent(Optional<WorkOrderDto> oWo) throws BusinessException {
		if ( oWo.isPresent() ) return;
		throw new BusinessException("There is no work order for this id"); 
	}
}
