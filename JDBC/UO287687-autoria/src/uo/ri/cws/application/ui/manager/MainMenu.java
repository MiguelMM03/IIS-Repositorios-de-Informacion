package uo.ri.cws.application.ui.manager;



import uo.ri.cws.application.ui.manager.mechanicManagement.MechanicMenu;
import uo.ri.cws.application.ui.manager.training.TrainingMenu;
import util.menu.BaseMenu;
import util.menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {

    public MainMenu() {
	menuOptions = new Object[][] { { "Manager", null },
		{ "Mechanics management", MechanicMenu.class },
		{ "Training management", TrainingMenu.class },
		{ "Spare parts management", NotYetImplementedAction.class },
		{ "Vehicle types management", NotYetImplementedAction.class },
		{ "Contracts management", NotYetImplementedAction.class },
		{ "Payroll management", NotYetImplementedAction.class }

	};
    }

    public static void main(String[] args) {
	new MainMenu().execute();
    }

}
