package uo.ri.cws.application.service.invoice.impl.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService.WorkOrderForInvoicingDto;
import uo.ri.cws.application.service.invoice.impl.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderState;
import uo.ri.util.assertion.ArgumentChecks;

public class FindNotInvoicedWorkOrdersByClientDni
		implements Command<List<WorkOrderForInvoicingDto>> {

	private String dni;

	public FindNotInvoicedWorkOrdersByClientDni(String dni) {
		ArgumentChecks.isNotEmpty(dni,"Dni can't be empty");
		this.dni = dni;
	}

	@Override
	public List<WorkOrderForInvoicingDto> execute() throws BusinessException {
		List<WorkOrder> wos = Factory.repository.forWorkOrder()
				.findNotInvoicedByDni(dni);
		List<WorkOrderForInvoicingDto> result=new ArrayList<>();
		for(WorkOrder wo:wos) {
			if(wo.getState().equals(WorkOrderState.FINISHED))
				result.add(DtoAssembler.toDto(wo));
		}
		return result;
	}
}
