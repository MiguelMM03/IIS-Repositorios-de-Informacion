package uo.ri.cws.ui.manager.training.enrollment;

import uo.ri.cws.ui.manager.training.enrollment.actions.ListAttendanceToCourseAction;
import uo.ri.cws.ui.manager.training.enrollment.actions.RegisterAttendanceAction;
import uo.ri.cws.ui.manager.training.enrollment.actions.RemoveAttendanceAction;
import uo.ri.util.menu.BaseMenu;

public class AttendanceMenu extends BaseMenu {

	public AttendanceMenu() {
		menuOptions = new Object[][] {
			{"Manager > Training management > Attendance", null},

			{ "Register", 			RegisterAttendanceAction.class },
			{ "Remove", 			RemoveAttendanceAction.class },
			{ "List attendance",	ListAttendanceToCourseAction.class },
		};
	}

}
