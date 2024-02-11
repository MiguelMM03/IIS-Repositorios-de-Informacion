package uo.ri.cws.application.business.workorder.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.business.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import util.assertion.Argument;

public class FindWorkOrderById implements Command<Optional<WorkOrderBLDto>> {

	private String workorderid;
	private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();

	public FindWorkOrderById(String id) {
		Argument.isNotEmpty(id);
		this.workorderid = id;
	}

	@Override
	public Optional<WorkOrderBLDto> execute() throws BusinessException {
		return WorkOrderAssembler.toBLDto(wog.findById(workorderid));
	}

}
