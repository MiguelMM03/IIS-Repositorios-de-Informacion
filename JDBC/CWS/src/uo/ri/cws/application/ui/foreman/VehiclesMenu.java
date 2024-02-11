package uo.ri.cws.application.ui.foreman;

import util.menu.BaseMenu;
import util.menu.NotYetImplementedAction;

public class VehiclesMenu extends BaseMenu {

    public VehiclesMenu() {
	menuOptions = new Object[][] {
		{ "Foreman > Vehicles management ", null },

		{ "Add vehicle", NotYetImplementedAction.class },
		{ "Update vehicle", NotYetImplementedAction.class },
		{ "Delete vehicle", NotYetImplementedAction.class },
		{ "List vehicles", NotYetImplementedAction.class }, };
    }

}
