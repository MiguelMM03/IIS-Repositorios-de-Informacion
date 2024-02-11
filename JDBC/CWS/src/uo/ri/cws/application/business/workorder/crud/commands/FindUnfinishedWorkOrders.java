package uo.ri.cws.application.business.workorder.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.business.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

public class FindUnfinishedWorkOrders implements Command<List<WorkOrderBLDto>> {

	private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();

	@Override
	public List<WorkOrderBLDto> execute() throws BusinessException {
		return WorkOrderAssembler.toBLDtoList(wog.findUnfinished());
	}
}
