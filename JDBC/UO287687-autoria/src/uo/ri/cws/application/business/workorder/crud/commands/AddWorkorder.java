package uo.ri.cws.application.business.workorder.crud.commands;


import java.time.LocalDateTime;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.business.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import util.assertion.Argument;

public class AddWorkorder implements Command<WorkOrderBLDto> {

	private WorkOrderBLDto workOrder;
	private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();
	private VehicleGateway vg = PersistenceFactory.forVehicle();

	public AddWorkorder(WorkOrderBLDto wo) {
		Argument.isNotNull(wo, "Work order cant' be null");
		Argument.isNotEmpty(wo.vehicleId, "Vehicle id can't be empty");
		this.workOrder = wo;
	}

	@Override
	public WorkOrderBLDto execute() throws BusinessException {
		initializeWorkOrder();
		checkVehicle();
		wog.add(WorkOrderAssembler.toWorkOrderDALDto(workOrder));
		return workOrder;
	}

	/**
	 * Set default values to add correctly
	 * @throws BusinessException
	 */
	private void initializeWorkOrder() throws BusinessException {
		workOrder.id=UUID.randomUUID().toString();
		workOrder.amount=0.0;
		workOrder.date=LocalDateTime.now();
		workOrder.invoiceId=null;
		workOrder.mechanicId=null;
		workOrder.status="OPEN";

	}

	private void checkVehicle() throws BusinessException {
		if (vg.findById(this.workOrder.vehicleId).isEmpty()) {
			throw new BusinessException("Vehicle doesn't exist");
		}
	}

}
