package uo.ri.cws.application.ui.manager.mechanicManagement;


import uo.ri.cws.application.ui.manager.action.AddMechanicAction;
import uo.ri.cws.application.ui.manager.action.DeleteMechanicAction;
import uo.ri.cws.application.ui.manager.action.FindAllMechanicsAction;
import uo.ri.cws.application.ui.manager.action.FindMechanicByIdAction;
import uo.ri.cws.application.ui.manager.action.UpdateMechanicAction;
import util.menu.BaseMenu;

public class MechanicMenu extends BaseMenu {

	public MechanicMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Mechanics management", null},
			
			{ "Add mechanic", 				AddMechanicAction.class }, 
			{ "Update mechanic", 	UpdateMechanicAction.class }, 
			{ "Delete mechanic", 				DeleteMechanicAction.class }, 
			{ "List mechanic", 				FindMechanicByIdAction.class},
			{ "List mechanics", 				FindAllMechanicsAction.class },
		};
	}

}
