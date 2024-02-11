package uo.ri.cws.application.ui.foreman.action;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.workorder.WorkOrderService;
import util.console.Console;
import util.menu.Action;
//Esta clase no se utiliza
public class DeleteWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		String workorderId = Console.readString("Work order id");

		WorkOrderService wos=BusinessFactory.forWorkOrderService();
		if (wos.findWorkOrderById(workorderId).isPresent()) {
			wos.deleteWorkOrder(workorderId);
			Console.println("Work order deleted");
		} else {
			Console.println("Work order doesn't exist");
		}
	}
}
