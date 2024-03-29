package uo.ri.cws.application.ui.manager.training.reports;


import uo.ri.cws.application.ui.manager.training.reports.actions.ListCertificationsByVehicleTypeAction;
import uo.ri.cws.application.ui.manager.training.reports.actions.ListTrainingByVehicleTypeAction;
import uo.ri.cws.application.ui.manager.training.reports.actions.ListTrainingOfMechanicAction;
import util.menu.BaseMenu;

public class ReportsMenu extends BaseMenu {

    public ReportsMenu() {
	menuOptions = new Object[][] {
		{ "Manager > Training management > Reports", null },

		{ "Training of a mechanic",
			ListTrainingOfMechanicAction.class },
		{ "Training by vehicle types",
			ListTrainingByVehicleTypeAction.class },
		{ "Certifications by vehicle type",
			ListCertificationsByVehicleTypeAction.class } };
    }

}
