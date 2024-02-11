package uo.ri.cws.application.ui.manager.action;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import util.console.Console;
import util.menu.Action;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String id = Console.readString("Type mechahic id to update");
		String name = Console.readString("Name");
		String surname = Console.readString("Surname");
		MechanicBLDto m = new MechanicBLDto();
		m.id = id;
		m.name = name;
		m.surname = surname;
		BusinessFactory.forMechanicService().updateMechanic(m);
		Console.println("Mechanic updated");
	}

}
