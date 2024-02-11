package uo.ri.cws.application.business.workorder.crud.commands;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.business.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import util.assertion.Argument;

public class UpdateWorkOrder implements Command<Void> {

	private WorkOrderBLDto workOrder;
	private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();
	private VehicleGateway vg = PersistenceFactory.forVehicle();

	public UpdateWorkOrder(WorkOrderBLDto wo) {
		Argument.isNotNull(wo, "Work order cant' be null");
		Argument.isNotNull(wo.description, "Description can't be null");
		Argument.isNotNull(wo.vehicleId, "Vehicle id can't be null");
		Argument.isNotEmpty(wo.vehicleId, "Vehicle id can't be empty");
		this.workOrder = wo;
	}

	@Override
	public Void execute() throws BusinessException {
		checkWorkOrder();
		checkVehicle();
		wog.update(WorkOrderAssembler.toWorkOrderDALDto(workOrder));
		return null;
	}

	private void checkWorkOrder() throws BusinessException {
		if (workOrder.description == null) {
			throw new BusinessException("Work order description can't be null");
		}
		if (!workOrder.status.equalsIgnoreCase("OPEN")
				&& !workOrder.status.equalsIgnoreCase("ASSIGNED")) {
			throw new BusinessException("Invalid work order status");
		}
		if (wog.findById(this.workOrder.id).isEmpty()) {
			throw new BusinessException("Work order doesn't exist");
		}
	}

	private void checkVehicle() throws BusinessException {
		if (vg.findById(this.workOrder.vehicleId).isEmpty()) {
			throw new BusinessException("Vehicle doesn't exist");
		}
	}

}
