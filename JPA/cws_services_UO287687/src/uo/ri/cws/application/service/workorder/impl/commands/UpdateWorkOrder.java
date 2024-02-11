package uo.ri.cws.application.service.workorder.impl.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.assertion.ArgumentChecks;

public class UpdateWorkOrder implements Command<Void> {

	private WorkOrderDto dto;

	public UpdateWorkOrder(WorkOrderDto dto) {
		ArgumentChecks.isNotNull(dto, "Work order can't be null");
		ArgumentChecks.isNotBlank(dto.id, "Id can't be empty");
		ArgumentChecks.isNotBlank(dto.description,
				"Description can't be empty");
		this.dto = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		WorkOrder wo = getWorkOrder();
		wo.setAmount(dto.amount);
		wo.setDescription(dto.description);
		return null;
	}

	private WorkOrder getWorkOrder() throws BusinessException {
		Optional<WorkOrder> wos = Factory.repository.forWorkOrder()
				.findById(dto.id);
		BusinessChecks.isTrue(wos.isPresent(), "Work order doesn't exist");
		WorkOrder wo = wos.get();
		BusinessChecks.hasVersion(wo, dto.version);
		BusinessChecks.isTrue(wo.isAssigned() || wo.isOpen(),
				"Invalid work order status");
		return wo;
	}
}
