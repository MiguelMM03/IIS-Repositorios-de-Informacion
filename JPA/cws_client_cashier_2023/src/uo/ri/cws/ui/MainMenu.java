package uo.ri.cws.ui;

import uo.ri.cws.ui.cashier.action.FindNotInvoicedWorkOrders;
import uo.ri.cws.ui.cashier.action.InvoiceWorkorderAction;
import uo.ri.util.menu.BaseMenu;
import uo.ri.util.menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {{
		menuOptions = new Object[][] { 
			{ "Cash", null },
			{ "Find not invoiced work orders", 	FindNotInvoicedWorkOrders.class }, 
			{ "Find work order by plate", 		NotYetImplementedAction.class }, 
			{ "Invoice work orders", 			InvoiceWorkorderAction.class },
			{ "Liquidate invoice", 				NotYetImplementedAction.class },
		};
}}