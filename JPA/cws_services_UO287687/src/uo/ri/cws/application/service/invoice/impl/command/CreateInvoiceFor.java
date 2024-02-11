package uo.ri.cws.application.service.invoice.impl.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.service.invoice.impl.DtoAssembler;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.assertion.ArgumentChecks;

public class CreateInvoiceFor implements Command<InvoiceDto> {

	private List<String> workOrderIds;
	private WorkOrderRepository wrkrsRepo = Factory.repository.forWorkOrder();
	private InvoiceRepository invsRepo = Factory.repository.forInvoice();

	public CreateInvoiceFor(List<String> workOrderIds) {
		ArgumentChecks.isNotNull(workOrderIds);
		ArgumentChecks.isFalse(workOrderIds.isEmpty(), "List can't be empty");
		workOrderIds.forEach(
				e -> ArgumentChecks.isNotBlank(e, "Elements can't be empty"));
		this.workOrderIds = workOrderIds;
	}

	@Override
	public InvoiceDto execute() throws BusinessException {
		List<WorkOrder> workOrders = wrkrsRepo.findByIds(workOrderIds);
		BusinessChecks.isFalse(workOrders.isEmpty(), "There are no workorders");
		BusinessChecks.isTrue(workOrders.size() == this.workOrderIds.size(),
				"Not all workorders exist");
		BusinessChecks.isTrue(finished(workOrders),
				"Not all workorders are finished");
		Long number = invsRepo.getNextInvoiceNumber();
		Invoice i = new Invoice(number, workOrders);
		invsRepo.add(i);
		return DtoAssembler.toDto(i);
	}

	private boolean finished(List<WorkOrder> workOrders) {
		for (WorkOrder w : workOrders) {
			if (!w.isFinished()) {
				return false;
			}
		}
		return true;
	}

}
