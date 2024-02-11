package uo.ri.cws.application.ui.foreman.reception;


import uo.ri.cws.application.ui.foreman.reception.action.AssignWorkOrderAction;
import uo.ri.cws.application.ui.foreman.reception.action.ListCertifiedMechanicsAction;
import uo.ri.cws.application.ui.foreman.reception.action.ListUnfinishedWorkOrdersAction;
import uo.ri.cws.application.ui.foreman.reception.action.ListWorkOrdersByPlateNumberAction;
import uo.ri.cws.application.ui.foreman.reception.action.RegisterWorkOrderAction;
import uo.ri.cws.application.ui.foreman.reception.action.RemoveWorkOrderAction;
import uo.ri.cws.application.ui.foreman.reception.action.UpdateWorkOrderAction;
import uo.ri.cws.application.ui.foreman.reception.action.ViewWorkOrderDetailAction;
import util.menu.BaseMenu;

public class ReceptionMenu extends BaseMenu {

    public ReceptionMenu() {
	menuOptions = new Object[][] { { "Foreman > Vehicle reception", null },

		{ "Register a work order", RegisterWorkOrderAction.class },
		{ "Update a work order", UpdateWorkOrderAction.class },
		{ "Remove a work order", RemoveWorkOrderAction.class },
		{ "", null },
		{ "List unfinished work orders",
			ListUnfinishedWorkOrdersAction.class },
		{ "List work orders by plate",
			ListWorkOrdersByPlateNumberAction.class },
		{ "View work order detail", ViewWorkOrderDetailAction.class },
		{ "", null },
		{ "List certified mechanics",
			ListCertifiedMechanicsAction.class },
		{ "Assign a work order", AssignWorkOrderAction.class }, };
    }

}
