package uo.ri.cws.application.ui.foreman.reception.action;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.vehicle.VehicleService;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import util.console.Console;

public class WorkOrderUserInteractor {

	public WorkOrderBLDto askForWorkOrder(VehicleBLDto v) {
		WorkOrderBLDto wo = new WorkOrderBLDto();
		wo.description = Console.readString("Work description");
		wo.vehicleId = v.id;
		return wo;
	}

	public VehicleBLDto askForVehicle() throws BusinessException {
		String plate = Console.readString("Plate number");

		VehicleService vs = BusinessFactory.forVehicleService();
		Optional<VehicleBLDto> ov = vs.findVehicleByPlate(plate);
		if (ov.isEmpty())
			throw new BusinessException("There is no such vehicle");
		return ov.get();
	}

}
