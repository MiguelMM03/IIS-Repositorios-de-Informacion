package uo.ri.cws.application.ui.manager.training.course;


import uo.ri.cws.application.ui.manager.training.course.actions.AddCourseAction;
import uo.ri.cws.application.ui.manager.training.course.actions.ListCoursesAction;
import uo.ri.cws.application.ui.manager.training.course.actions.RemoveCourseAction;
import uo.ri.cws.application.ui.manager.training.course.actions.UpdateCourseAction;
import util.menu.BaseMenu;

public class CourseCrudMenu extends BaseMenu {

    public CourseCrudMenu() {
	menuOptions = new Object[][] {
		{ "Manager > Training management > Course CRUD", null },

		{ "Add new course", AddCourseAction.class },
		{ "Update a course", UpdateCourseAction.class },
		{ "Remove a course", RemoveCourseAction.class },
		{ "List all courses", ListCoursesAction.class }, };
    }

}
