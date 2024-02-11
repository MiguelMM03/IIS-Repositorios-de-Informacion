package uo.ri.cws.ui.cashier.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.invoice.InvoicingService.WorkOrderForInvoicingDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class FindNotInvoicedWorkOrders implements Action {

	@Override
	public void execute() throws BusinessException {
		InvoicingService cs = Factory.service.forInvoicingService();

		String dni = Console.readString("Client dni");

		Console.println("\nInvoice-pending work orders\n");

		List<WorkOrderForInvoicingDto> reps = 
				cs.findNotInvoicedWorkOrdersByClientDni( dni );

		if (reps.size() == 0) {
			Console.printf("There is no pending work orders\n");
			return;
		}

		for(WorkOrderForInvoicingDto rep : reps) {
			Printer.printWorkOrder( rep );
		}
	}

}
