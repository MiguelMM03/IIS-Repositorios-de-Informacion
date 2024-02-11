package uo.ri.cws.application.service.invoice.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.invoice.impl.command.CreateInvoiceFor;
import uo.ri.cws.application.service.invoice.impl.command.FindNotInvoicedWorkOrdersByClientDni;
import uo.ri.cws.application.util.command.CommandExecutor;

public class InvoicingServiceImpl implements InvoicingService {

	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public InvoiceDto createInvoiceFor(List<String> woIds)
			throws BusinessException {

		return executor.execute( new CreateInvoiceFor( woIds) );
	}

	@Override
	public List<WorkOrderForInvoicingDto> findWorkOrdersByClientDni(String dni)
			throws BusinessException {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public List<PaymentMeanDto> findPayMeansByClientDni(String dni)
			throws BusinessException {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public List<WorkOrderForInvoicingDto> findNotInvoicedWorkOrdersByClientDni(
			String dni) throws BusinessException {
		return executor.execute(new FindNotInvoicedWorkOrdersByClientDni(dni));
	}

	@Override
	public List<WorkOrderForInvoicingDto> findWorkOrdersByPlateNumber(
			String plate) throws BusinessException {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public Optional<InvoiceDto> findInvoiceByNumber(Long number)
			throws BusinessException {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void settleInvoice(String invoiceId, List<ChargeDto> charges)
			throws BusinessException {
		throw new RuntimeException("Not yet implemented");
	}

}
