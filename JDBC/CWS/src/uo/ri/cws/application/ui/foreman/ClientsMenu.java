package uo.ri.cws.application.ui.foreman;

import util.menu.BaseMenu;
import util.menu.NotYetImplementedAction;

public class ClientsMenu extends BaseMenu {

    public ClientsMenu() {
	menuOptions = new Object[][] {
		{ "Foreman > Customer management", null },

		{ "Add customer", NotYetImplementedAction.class },
		{ "Update customer", NotYetImplementedAction.class },
		{ "Delete customer", NotYetImplementedAction.class },
		{ "List customers", NotYetImplementedAction.class }, };
    }

}
