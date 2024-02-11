package uo.ri.cws.application.service.workorder.impl.commands;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.assertion.ArgumentChecks;

public class AddWorkOrder implements Command<WorkOrderDto> {

	private WorkOrderDto dto;

	public AddWorkOrder(WorkOrderDto dto) {
		ArgumentChecks.isNotNull(dto, "Work order can't be null");
		ArgumentChecks.isNotBlank(dto.vehicleId, "Vehicle id can't be empty");
		ArgumentChecks.isNotBlank(dto.description,
				"Description can't be empty");
		this.dto = dto;
	}

	@Override
	public WorkOrderDto execute() throws BusinessException {
		Optional<Vehicle> vehicle = Factory.repository.forVehicle()
				.findById(dto.vehicleId);
		BusinessChecks.isTrue(vehicle.isPresent(), "Work order doesn't exist");
		checkWorkOrder(vehicle.get());
		WorkOrder wo = new WorkOrder(vehicle.get(), dto.date, dto.description);
		Factory.repository.forWorkOrder().add(wo);
		dto.id = wo.getId();
		return dto;
	}

	private void checkWorkOrder(Vehicle v) throws BusinessException {
		List<WorkOrder> wos = Factory.repository.forWorkOrder()
				.findWorkOrdersByVehicleId(dto.vehicleId);
		for (WorkOrder wo : wos) {
			BusinessChecks.isFalse(
					wo.getVehicle().getVehicleType().equals(v.getVehicleType())
							&& wo.getDate().equals(dto.date),
					"Work order already exist");
		}
	}

}
