package uo.ri.cws.ui.util;

import java.util.List;

import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.service.invoice.InvoicingService.PaymentMeanDto;
import uo.ri.cws.application.service.invoice.InvoicingService.WorkOrderForInvoicingDto;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.util.console.Console;

public class Printer {

	public static void printInvoice(InvoiceDto invoice) {

		double importeConIVa = invoice.amount;
		double iva =  invoice.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Factura nº: %d\n", 				invoice.number );
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", 	invoice.date);
		Console.printf("\tTotal: %.2f €\n", 			importeSinIva);
		Console.printf("\tIva: %.1f %% \n", 			invoice.vat );
		Console.printf("\tTotal con IVA: %.2f €\n", 	invoice.amount );
		Console.printf("\tEstado: %s\n", 				invoice.state );
	}

	public static void printPaymentMeans(List<PaymentMeanDto> medios) {
		Console.println();
		Console.println("Medios de pago disponibles");
		
		Console.printf("\t%s \t%-8.8s \t%s \n", "ID", "Tipo", "Acumulado");
		for (PaymentMeanDto medio : medios) {
			printPaymentMean( medio );
		}
	}

	private static void printPaymentMean(PaymentMeanDto medio) {
		Console.printf("\t%s \t%-8.8s \t%s \n", 
				medio.id,
				medio.getClass().getName(),  // not the best...
				medio.accumulated
		);
	}

	public static void printWorkOrder(WorkOrderForInvoicingDto rep) {
		
		Console.printf("\t%d \t%-40.40s \t%td/%<tm/%<tY \t%-12.12s \t%.2f\n",  
				rep.id
				, rep.description 
				, rep.date
				, rep.state
				, rep.amount
		);
	}

	public static void printMechanic(MechanicDto m) {

		Console.printf("\t%d %-10.10s %-25.25s %-25.25s\n",  
				m.id
				, m.dni
				, m.name
				, m.surname
			);
	}

	public static void printRuntimeException(RuntimeException rte) {
		System.err.println("The has been a system error. "
				+ "Please contact the technical service");
		rte.printStackTrace();
	}

}
