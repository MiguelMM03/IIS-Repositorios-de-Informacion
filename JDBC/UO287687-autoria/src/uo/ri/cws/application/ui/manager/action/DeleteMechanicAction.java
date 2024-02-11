package uo.ri.cws.application.ui.manager.action;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import util.console.Console;
import util.menu.Action;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		String idMechanic = Console.readString("Type mechanic id ");
		BusinessFactory.forMechanicService().deleteMechanic(idMechanic);
		Console.println("Mechanic deleted");

	}

}
