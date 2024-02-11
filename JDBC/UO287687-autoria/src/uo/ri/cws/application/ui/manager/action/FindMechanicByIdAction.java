package uo.ri.cws.application.ui.manager.action;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class FindMechanicByIdAction implements Action{

	
	@Override
	public void execute() {
		String idMechanic = Console.readString("Type mechanic id "); 
		Optional<MechanicBLDto> mechanic;
		try {
			mechanic = BusinessFactory.forMechanicService().findMechanicById(idMechanic);
			if(mechanic.isPresent())
				Printer.printMechanic(mechanic.get());
			else
				System.out.println("Mechanic doesn't exist");
		} catch (BusinessException e) {
			System.out.println("Mechanic doesn't exist");
		}
		
	}
	
}
