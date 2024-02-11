package uo.ri.cws.application.business.invoice.assembler;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.invoice.InvoiceService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoiceService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public class InvoiceAssembler {

	public static InvoiceBLDto toDto(InvoiceDALDto arg) {
		InvoiceBLDto result = new InvoiceBLDto();
		result.id = arg.id;
		result.number = arg.number;
		result.status = arg.status;
		result.date = arg.date;
		result.total = arg.amount;
		result.vat = arg.vat;
		return result;
	}

	public static List<WorkOrderForInvoicingBLDto> toInvoicingWorkOrderList(List<WorkOrderDALDto> arg) {
		List<WorkOrderForInvoicingBLDto> result = new ArrayList<WorkOrderForInvoicingBLDto>();
		for (WorkOrderDALDto record : arg)
			result.add(toDto(record));
		return result;
	}

	private static WorkOrderForInvoicingBLDto toDto(WorkOrderDALDto record) {
		WorkOrderForInvoicingBLDto dto = new WorkOrderForInvoicingBLDto();
		dto.id = record.id;
		dto.date = record.date;
		dto.description = record.description;
		dto.date = record.date;
		dto.status = record.status;
		dto.total = record.amount;

		return dto;
	}

	public static List<InvoiceBLDto> toDtoList(List<InvoiceDALDto> dalDtoList) {
		List<InvoiceBLDto> result = new ArrayList<InvoiceBLDto>();
		for (InvoiceDALDto record : dalDtoList)
			result.add(toDto(record));
		return result;
	}
	
	public static InvoiceDALDto toDALDto(InvoiceBLDto invoice) {
		InvoiceDALDto dald=new InvoiceDALDto();
		dald.id=invoice.id;
		dald.amount=invoice.total;
		dald.date=invoice.date;
		dald.number=invoice.number;
		dald.status=invoice.status;
		dald.version=invoice.version;
		dald.vat=invoice.vat;
		return dald;
	}
}
