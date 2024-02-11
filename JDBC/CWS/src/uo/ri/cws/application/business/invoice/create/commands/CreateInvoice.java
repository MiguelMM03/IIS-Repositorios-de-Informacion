package uo.ri.cws.application.business.invoice.create.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoiceService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoiceService.InvoiceStatus;
import uo.ri.cws.application.business.invoice.assembler.InvoiceAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;
import util.assertion.Argument;
import util.math.Round;

public class CreateInvoice implements Command<InvoiceBLDto> {
	private List<String> workOrderIds;
	private InvoiceGateway ig = PersistenceFactory.forInvoice();
	private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();
	private InvoiceBLDto invoice;

	public CreateInvoice(List<String> workOrderIds) {
		Argument.isNotNull(workOrderIds);
		if (workOrderIds.isEmpty()) {
			throw new IllegalArgumentException("Work order list can't be empty");
		}
		checkWorkOrderIds(workOrderIds);
		this.workOrderIds = workOrderIds;

	}

	private void checkWorkOrderIds(List<String> workOrderIds) {
		for (String id : workOrderIds) {
			Argument.isNotEmpty(id);
		}

	}

	@Override
	public InvoiceBLDto execute() throws BusinessException {
		if (!checkWorkOrdersExist(workOrderIds))
			throw new BusinessException("Workorder does not exist");
		if (!checkWorkOrdersFinished(workOrderIds))
			throw new BusinessException("Workorder is not finished yet");
		this.invoice = new InvoiceBLDto();
		long numberInvoice = generateInvoiceNumber();
		LocalDate dateInvoice = LocalDate.now();
		double amount = calculateTotalInvoice(workOrderIds); // vat not included
		double vat = vatPercentage(amount, dateInvoice);
		double total = amount * (1 + vat / 100); // vat included
		total = Round.twoCents(total);
		String idInvoice = createInvoice(numberInvoice, dateInvoice, vat, total);
		this.invoice.id = idInvoice;
		this.invoice.number = numberInvoice;
		this.invoice.date = dateInvoice;
		this.invoice.total = total;
		this.invoice.vat = vat;
		this.invoice.status = InvoiceStatus.NOT_YET_PAID.getStatus();
		updateWorkOrders(workOrderIds, this.invoice);
		return this.invoice;
	}

	/*
	 * checks whether every work order exist
	 */
	private boolean checkWorkOrdersExist(List<String> workOrderIDS) {
		for (String id : workOrderIDS) {
			Optional<WorkOrderDALDto> wo = wog.findById(id);
			if (wo.isEmpty())
				return false;
		}
		return true;
	}

	/*
	 * checks whether every work order id is FINISHED
	 */
	private boolean checkWorkOrdersFinished(List<String> workOrderIDS) throws BusinessException {
		for (String id : workOrderIDS) {
			Optional<WorkOrderDALDto> wo = wog.findById(id);
			if (!wo.get().status.equalsIgnoreCase("FINISHED"))
				return false;
		}
		return true;
	}

	/*
	 * Generates next invoice number (not to be confused with the inner id)
	 */
	private Long generateInvoiceNumber() {
		return ig.getNextInvoiceNumber();
	}

	/*
	 * Compute total amount of the invoice (as the total of individual work orders'
	 * amount
	 */
	private double calculateTotalInvoice(List<String> workOrderIDS) throws BusinessException {

		double totalInvoice = 0.0;
		for (String workOrderID : workOrderIDS) {
			totalInvoice += getWorkOrderTotal(workOrderID);
		}
		return totalInvoice;
	}

	/*
	 * checks whether every work order id is FINISHED
	 */
	private Double getWorkOrderTotal(String workOrderID) throws BusinessException {
		Optional<WorkOrderDALDto> wo = wog.findById(workOrderID);
		if (wo.isEmpty()) {
			throw new BusinessException("Workorder " + workOrderID + " doesn't exist");
		}

		return wo.get().amount;

	}

	/*
	 * returns vat percentage
	 */
	private double vatPercentage(double totalInvoice, LocalDate dateInvoice) {
		return LocalDate.parse("2012-07-01").isBefore(dateInvoice) ? 21.0 : 18.0;

	}

	/*
	 * Creates the invoice in the database; returns the id
	 */
	private String createInvoice(long numberInvoice, LocalDate dateInvoice, double vat, double total) {

		InvoiceBLDto invoice = new InvoiceBLDto();
		invoice.id = UUID.randomUUID().toString();
		;
		invoice.number = numberInvoice;
		invoice.date = dateInvoice;
		invoice.vat = vat;
		invoice.total = total;
		invoice.status = "NOT_YET_PAID";
		invoice.version = 1L;
		ig.add(InvoiceAssembler.toDALDto(invoice));
		return invoice.id;
	}

	/*
	 * Actualiza las Work Orders
	 */
	private void updateWorkOrders(List<String> ids, InvoiceBLDto invoice) {
		for (String id : workOrderIds) {
			Optional<WorkOrderDALDto> wo = wog.findById(id);
			wo.get().status = "INVOICED";
			wo.get().invoiceId = invoice.id;
			wog.update(wo.get());
		}
	}
}