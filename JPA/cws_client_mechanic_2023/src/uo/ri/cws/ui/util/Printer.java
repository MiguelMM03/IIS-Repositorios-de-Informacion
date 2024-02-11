package uo.ri.cws.ui.util;

import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.util.console.Console;

public class Printer {

	public static void printWorkOrder(WorkOrderDto rep) {
		
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

}
