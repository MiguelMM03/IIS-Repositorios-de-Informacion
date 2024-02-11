package uo.ri.cws.application.service.workorder.impl.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.cws.application.service.workorder.impl.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.util.assertion.ArgumentChecks;

public class FindWorkOrderById implements Command<Optional<WorkOrderDto>> {

	private String id;

	public FindWorkOrderById(String id) {
		ArgumentChecks.isNotBlank(id, "Work order id can't be empty");
		this.id = id;
	}

	@Override
	public Optional<WorkOrderDto> execute() throws BusinessException {
		return Factory.repository.forWorkOrder().findById(id).isPresent()
				? Optional.of(DtoAssembler.toDto(
						Factory.repository.forWorkOrder().findById(id).get()))
				: Optional.empty();
	}

}
