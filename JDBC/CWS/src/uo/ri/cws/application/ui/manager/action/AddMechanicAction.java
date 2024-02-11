package uo.ri.cws.application.ui.manager.action;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import util.console.Console;
import util.menu.Action;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String dni = Console.readString("Dni");
		String name = Console.readString("Name");
		String surname = Console.readString("Surname");

		MechanicBLDto m = new MechanicBLDto();
		m.dni = dni;
		m.name = name;
		m.surname = surname;
		m = BusinessFactory.forMechanicService().addMechanic(m);
		Console.println("Mechanic added");
	}

}
