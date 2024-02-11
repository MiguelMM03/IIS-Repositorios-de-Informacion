package uo.ri.cws.application.ui.manager.training;


import uo.ri.cws.application.ui.manager.training.certificates.FindCertificatesBeforeDateByMechanicAction;
import uo.ri.cws.application.ui.manager.training.certificates.GenerateCertificatesAction;
import uo.ri.cws.application.ui.manager.training.reports.ReportsMenu;
import util.menu.BaseMenu;
import util.menu.NotYetImplementedAction;

public class TrainingMenu extends BaseMenu {

    public TrainingMenu() {
	menuOptions = new Object[][] {
		{ "Manager > Training management", null },

		{ "Course management", NotYetImplementedAction.class },
		{ "Attendance registration", NotYetImplementedAction.class },
		{ "Reports", ReportsMenu.class }, { "", null },
		{ "Certificate generation", GenerateCertificatesAction.class },
		{ "Find certificate before year by mechanic", FindCertificatesBeforeDateByMechanicAction.class},

	};
    }

}
