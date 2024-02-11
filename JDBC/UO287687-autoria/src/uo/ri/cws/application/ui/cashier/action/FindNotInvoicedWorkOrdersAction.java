package uo.ri.cws.application.ui.cashier.action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.InvoiceService;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class FindNotInvoicedWorkOrdersAction implements Action {
	

	/**
	 * Process:
	 * 
	 *   - Ask customer dni
	 *    
	 *   - Display all uncharged workorder  
	 *   		(status <> 'INVOICED'). For each workorder, display 
	 *   		id, vehicle id, date, status, amount and description
	 */


	@Override
	public void execute() throws BusinessException {
		String dni = Console.readString("Client DNI ");
		InvoiceService is=BusinessFactory.forInvoicingService();
		Printer.printInvoicingWorkOrders(is.findNotInvoicedWorkOrdersByClientDni(dni));

		
	}

}