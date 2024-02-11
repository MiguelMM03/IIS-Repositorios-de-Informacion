package uo.ri.cws.application.ui.foreman.reception.action;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class ViewWorkOrderDetailAction implements Action {

    @Override
    public void execute() throws BusinessException {

	String woId = Console.readString("Work order id");

	Optional<WorkOrderBLDto> owo = BusinessFactory.forWorkOrderService().findWorkOrderById(woId);
	if (owo.isEmpty())
	    throw new BusinessException("There is no work order for this id");
	WorkOrderBLDto wo = owo.get();

	Printer.printWorkOrderDetail(wo);

    }
}
