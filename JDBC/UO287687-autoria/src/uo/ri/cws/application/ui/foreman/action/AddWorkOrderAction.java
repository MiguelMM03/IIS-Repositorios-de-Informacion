package uo.ri.cws.application.ui.foreman.action;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import util.console.Console;
import util.menu.Action;
//Esta clase no se utiliza
public class AddWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		// Get info
		String vehiclePlate = Console.readString("Vehicle plate number"); 
		String description = Console.readString("Description"); 
		double amount = Console.readDouble("Amount");
		
		
		WorkOrderBLDto wo=new WorkOrderBLDto();
		Optional<VehicleBLDto> vehicle=BusinessFactory.forVehicleService().findVehicleByPlate(vehiclePlate);
		if(vehicle.isPresent()) {
			wo.vehicleId=BusinessFactory.forVehicleService().findVehicleByPlate(vehiclePlate).get().id;
			wo.description=description;
			wo.amount=amount;
			wo=BusinessFactory.forWorkOrderService().registerNew(wo);
			Console.println("Work order added");
		}
		
	}

}
