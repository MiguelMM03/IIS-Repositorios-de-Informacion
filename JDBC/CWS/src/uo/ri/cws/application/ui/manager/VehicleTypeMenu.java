package uo.ri.cws.application.ui.manager;

import util.menu.BaseMenu;
import util.menu.NotYetImplementedAction;

public class VehicleTypeMenu extends BaseMenu {

	public VehicleTypeMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Vehicle type management", null},

			{ "Add vehicle type ", 				NotYetImplementedAction.class }, 
			{ "Update vehicle type ", 	NotYetImplementedAction.class }, 
			{ "Delete vehicle type ", 				NotYetImplementedAction.class }, 
			{ "List vehicle types", 				NotYetImplementedAction.class },
		};
	}

}
