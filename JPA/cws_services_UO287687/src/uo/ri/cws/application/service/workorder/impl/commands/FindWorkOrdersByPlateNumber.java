package uo.ri.cws.application.service.workorder.impl.commands;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.cws.application.service.workorder.impl.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.util.assertion.ArgumentChecks;

public class FindWorkOrdersByPlateNumber
		implements Command<List<WorkOrderDto>> {

	private String number;

	public FindWorkOrdersByPlateNumber(String number) {
		ArgumentChecks.isNotBlank(number, "Plate number can't be empty");
		this.number = number;
	}

	@Override
	public List<WorkOrderDto> execute() throws BusinessException {
		return DtoAssembler.toWorkOrderDtoList(Factory.repository.forWorkOrder()
				.findWorkOrdersByPlate(number));
	}
}
