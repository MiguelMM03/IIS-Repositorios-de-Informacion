package uo.ri.cws.application.ui.manager.training.enrollment;


import uo.ri.cws.application.ui.manager.training.enrollment.action.ListEnrollmentToCourseAction;
import uo.ri.cws.application.ui.manager.training.enrollment.action.RegisterEnrollmentAction;
import uo.ri.cws.application.ui.manager.training.enrollment.action.RemoveEnrollmentAction;
import util.menu.BaseMenu;

public class EnrollmentMenu extends BaseMenu {

    public EnrollmentMenu() {
	menuOptions = new Object[][] {
		{ "Manager > Training management > Attendance", null },
		{ "Register", RegisterEnrollmentAction.class },
		{ "Remove", RemoveEnrollmentAction.class },
		{ "List attendance", ListEnrollmentToCourseAction.class }, };
    }

}
