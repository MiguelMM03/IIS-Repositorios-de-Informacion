package uo.ri.cws.ui;

import uo.ri.cws.ui.manager.mechanic.MechanicsMenu;
import uo.ri.cws.ui.manager.training.TrainingMenu;
import uo.ri.util.menu.BaseMenu;
import uo.ri.util.menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {{
		menuOptions = new Object[][] { 
			{ "Manager", null },
			
			{ "Mechanics management", 		MechanicsMenu.class },
			{ "Spareparts management", 		NotYetImplementedAction.class },
			{ "Vehicle types management", 	NotYetImplementedAction.class }, 
			{ "Training management", 		TrainingMenu.class }, 
		};
}}