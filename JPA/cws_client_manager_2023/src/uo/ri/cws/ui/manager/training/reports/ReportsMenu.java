package uo.ri.cws.ui.manager.training.reports;

import uo.ri.cws.ui.manager.training.reports.actions.ListTrainingByVehicleTypeAction;
import uo.ri.util.menu.BaseMenu;
import uo.ri.util.menu.NotYetImplementedAction;

public class ReportsMenu extends BaseMenu {

	public ReportsMenu() {
		menuOptions = new Object[][] {
			{ "Manager > Training management > Reports", null },

			{ "Training of a mechanic",
				NotYetImplementedAction.class },
			{ "Training by vehicle types",
					ListTrainingByVehicleTypeAction.class },
			{ "Certifications by vehicle type",
						NotYetImplementedAction.class } 
		};
	}

}
