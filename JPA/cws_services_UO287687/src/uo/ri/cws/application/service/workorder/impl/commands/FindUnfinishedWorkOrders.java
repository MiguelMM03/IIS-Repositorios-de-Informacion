package uo.ri.cws.application.service.workorder.impl.commands;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.cws.application.service.workorder.impl.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

public class FindUnfinishedWorkOrders implements Command<List<WorkOrderDto>> {

	@Override
	public List<WorkOrderDto> execute() throws BusinessException {
		return DtoAssembler.toWorkOrderDtoList(Factory.repository.forWorkOrder()
				.findUnfinishedWorkOrders());
	}
}
