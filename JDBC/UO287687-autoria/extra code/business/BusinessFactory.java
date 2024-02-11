package uo.ri.cws.application.business;

import uo.ri.cws.application.business.client.ClientService;
import uo.ri.cws.application.business.invoice.InvoiceService;
import uo.ri.cws.application.business.invoice.create.InvoiceServiceImpl;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.MechanicServiceImpl;

public class BusinessFactory {

	
	public static MechanicService forMechanicService() {
		return new MechanicServiceImpl();
	}

	
	public static InvoiceService forInvoicingService() {
		return new InvoiceServiceImpl();
	}

	public static ClientService forClientService() {
		throw new RuntimeException("Not yet implemented");
	}

}
