package uo.ri.cws.application.ui.manager.action;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.ui.util.Printer;
import util.menu.Action;

public class FindAllMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Printer.printMechanics(BusinessFactory.forMechanicService().findAllMechanics());

	}
}
