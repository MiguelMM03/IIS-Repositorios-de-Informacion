package uo.ri.cws.application.ui.foreman.reception.action;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import util.console.Console;
import util.menu.Action;

public class UpdateWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String woId = Console.readString("Work order id");

		Optional<WorkOrderBLDto> owo = BusinessFactory.forWorkOrderService().findWorkOrderById(woId);
		if (owo.isEmpty())
			throw new BusinessException("There is no work order for this id");
		WorkOrderBLDto wo = owo.get();
		/*
		 * it can also be checked here the work order is in OPEN or ASSIGNED status. It
		 * is checked by the service anyway, but doing it here improves the user
		 * experience by detecting the problem earlier.
		 */
		if (!wo.status.equalsIgnoreCase("OPEN") && !wo.status.equalsIgnoreCase("ASSIGNED")) {
			throw new BusinessException("Invalid work order status");
		}
		String description = Console.readString("New description");
		wo.description = description;

		BusinessFactory.forWorkOrderService().updateWorkOrder(wo);

		Console.println("\nUpdate done");
	}

}
