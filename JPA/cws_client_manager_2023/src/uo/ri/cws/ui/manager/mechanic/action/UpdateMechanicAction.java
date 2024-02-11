package uo.ri.cws.ui.manager.mechanic.action;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		String id = Console.readString("Mechanic id"); 
		
		MechanicCrudService as = Factory.service.forMechanicCrudService();

		Optional<MechanicDto> res = as.findMechanicById(id);
		if ( ! res.isPresent() /* res.isEmpty() */) {
			throw new BusinessException("There is no mechanic with that id");
		}
		MechanicDto m = res.get();
		Printer.printMechanic(m);
		
		m.name = Console.readString("Name"); 
		m.surname = Console.readString("Surname"); 
		
		as.updateMechanic( m );
		
		Console.println("The mechanic has been updated");
	}

}
