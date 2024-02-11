package uo.ri.cws.application.ui.foreman;


import uo.ri.cws.application.ui.foreman.reception.ReceptionMenu;
import util.menu.BaseMenu;
import util.menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {

    public MainMenu() {
	menuOptions = new Object[][] { { "Foreman", null },
		{ "Client reception ", ReceptionMenu.class },
		{ "Client management", ClientsMenu.class },
		{ "Vehicle management", VehiclesMenu.class },
		{ "Client history review", NotYetImplementedAction.class }, };
    }

    public static void main(String[] args) {
    	new MainMenu().execute();
    }

}
