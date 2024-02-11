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
	public void execute() throws BusinessException{
		String idMechanic = Console.readString("Type mechanic id "); 
		Optional<MechanicBLDto> mechanic;
		mechanic = BusinessFactory.forMechanicService().findMechanicById(idMechanic);
		if(mechanic.isPresent())
			Printer.printMechanic(mechanic.get());
		else
			System.out.println("Mechanic doesn't exist");
		
	}
	
}
