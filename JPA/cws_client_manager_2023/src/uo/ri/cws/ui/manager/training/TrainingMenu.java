package uo.ri.cws.ui.manager.training;

import uo.ri.cws.ui.manager.training.certificates.GenerateCertificatesAction;
import uo.ri.cws.ui.manager.training.reports.ReportsMenu;
import uo.ri.util.menu.BaseMenu;
import uo.ri.util.menu.NotYetImplementedAction;

public class TrainingMenu extends BaseMenu {

	public TrainingMenu() {
		menuOptions = new Object[][] {
			{"Manager > Training management", null},

			{ "Course management", 			NotYetImplementedAction.class },
			{ "Attendance registration", 	NotYetImplementedAction.class },
			{ "Reports", 					ReportsMenu.class },
			{ "", null },
			{ "Certificate generation", 	GenerateCertificatesAction.class },

		};
	}

}
