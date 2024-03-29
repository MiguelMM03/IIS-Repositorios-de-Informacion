package uo.ri.cws.ui.manager.training.course;

import uo.ri.cws.ui.manager.training.course.actions.AddCourseAction;
import uo.ri.cws.ui.manager.training.course.actions.ListCoursesAction;
import uo.ri.cws.ui.manager.training.course.actions.RemoveCourseAction;
import uo.ri.cws.ui.manager.training.course.actions.UpdateCourseAction;
import uo.ri.util.menu.BaseMenu;

public class CourseCrudMenu extends BaseMenu {

	public CourseCrudMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Training management > Course CRUD", null},
			
			{ "Add", 			AddCourseAction.class }, 
			{ "Update", 		UpdateCourseAction.class }, 
			{ "Remove", 		RemoveCourseAction.class }, 
			{ "List all", 		ListCoursesAction.class },
		};
	}

}
