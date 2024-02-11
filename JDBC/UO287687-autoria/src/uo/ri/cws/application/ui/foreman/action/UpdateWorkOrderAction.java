package uo.ri.cws.application.ui.foreman.action;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.workorder.WorkOrderService;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import util.console.Console;
import util.menu.Action;
//Esta clase no se utiliza
public class UpdateWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		String workOrderId = Console.readString("Work order id"); 
		String description = Console.readString("Description"); 
		
		WorkOrderService wos = BusinessFactory.forWorkOrderService();
		Optional<WorkOrderBLDto> wo=wos.findWorkOrderById(workOrderId);
		if(wo.isPresent()) {
			wo.get().description=description;
			wos.updateWorkOrder(wo.get());
			Console.println("Work order updated");
		}
		else {
			Console.println("Work order doesn't exist");
		}
	}

}
