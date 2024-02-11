package uo.ri.cws.ui.foreman.reception.actions;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.vehicle.VehicleCrudService;
import uo.ri.cws.application.service.vehicle.VehicleCrudService.VehicleDto;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.util.console.Console;

public class WorkOrderUserInteractor {

	public WorkOrderDto askForWorkOrder(VehicleDto v) {
		WorkOrderDto wo = new WorkOrderDto();
		wo.description = Console.readString("Work description");
		wo.vehicleId = v.id;
		return wo;
	}

	public VehicleDto askForVehicle() throws BusinessException {
		String plate = Console.readString("Plate number");
		
		VehicleCrudService vs = Factory.service.forVehicleCrudService();
		Optional<VehicleDto> ov = vs.findVehicleByPlate(plate);
		assertPresent(ov);
		
		return ov.get();
	}

	private void assertPresent(Optional<?> o) throws BusinessException {
		if ( o.isPresent() ) return;
		throw new BusinessException("There is no such entity"); 
	}

}
