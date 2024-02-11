package uo.ri.cws.application.service.invoice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uo.ri.cws.application.service.invoice.InvoicingService.CardDto;
import uo.ri.cws.application.service.invoice.InvoicingService.CashDto;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.service.invoice.InvoicingService.PaymentMeanDto;
import uo.ri.cws.application.service.invoice.InvoicingService.VoucherDto;
import uo.ri.cws.application.service.invoice.InvoicingService.WorkOrderForInvoicingDto;
import uo.ri.cws.domain.Cash;
import uo.ri.cws.domain.CreditCard;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.PaymentMean;
import uo.ri.cws.domain.Voucher;
import uo.ri.cws.domain.WorkOrder;

public class DtoAssembler {

	public static List<VoucherDto> toVoucherDtoList(List<Voucher> list) {
		List<VoucherDto> res = new ArrayList<>();
		for(Voucher b: list) {
			res.add( toDto( b ) );
		}
		return res;
	}

	public static VoucherDto toDto(Voucher v) {
		VoucherDto dto = new VoucherDto();
		dto.id = v.getId();
		dto.version = v.getVersion();

		dto.clientId = v.getClient().getId();
		dto.accumulated = v.getAccumulated();
		dto.code = v.getCode();
		dto.description = v.getDescription();
		dto.available = v.getAvailable();
		return dto;
	}

	public static CardDto toDto(CreditCard cc) {
		CardDto dto = new CardDto();
		dto.id = cc.getId();
		dto.version = cc.getVersion();

		dto.clientId = cc.getClient().getId();
		dto.accumulated = cc.getAccumulated();
		dto.cardNumber = cc.getNumber();
		dto.cardExpiration = cc.getValidThru();
		dto.cardType = cc.getType();
		return dto;
	}

	public static CashDto toDto(Cash m) {
		CashDto dto = new CashDto();
		dto.id = m.getId();
		dto.version = m.getVersion();

		dto.clientId = m.getClient().getId();
		dto.accumulated = m.getAccumulated();
		return dto;
	}

	public static InvoiceDto toDto(Invoice invoice) {
		InvoiceDto dto = new InvoiceDto();
		dto.id = invoice.getId();
		dto.version = invoice.getVersion();

		dto.number = invoice.getNumber();
		dto.date = invoice.getDate();
		dto.amount = invoice.getAmount();
		dto.vat = invoice.getVat();
		dto.state = invoice.getState().toString();
		return dto;
	}

	public static List<PaymentMeanDto> toPaymentMeanDtoList(List<PaymentMean> list) {
		return list.stream()
				.map( mp -> toDto( mp ) )
				.collect( Collectors.toList() );
	}

	public static PaymentMeanDto toDto(PaymentMean mp) {
		if (mp instanceof Voucher) {
			return toDto( (Voucher) mp );
		}
		else if (mp instanceof CreditCard) {
			return toDto( (CreditCard) mp );
		}
		else if (mp instanceof Cash) {
			return toDto( (Cash) mp);
		}
		else {
			throw new RuntimeException("Unexpected type of payment mean");
		}
	}

	public static WorkOrderForInvoicingDto toDto(WorkOrder a) {
		WorkOrderForInvoicingDto dto = new WorkOrderForInvoicingDto();
		dto.id = a.getId();
		dto.version = a.getVersion();

		dto.vehicleId = a.getVehicle().getId();
		dto.description = a.getDescription();
		dto.date = a.getDate();
		dto.amount = a.getAmount();
		dto.state = a.getState().toString();

		dto.invoiceId = a.getInvoice() == null ? null : a.getInvoice().getId();

		return dto;
	}

	public static List<WorkOrderForInvoicingDto> toWorkOrderDtoList(List<WorkOrder> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

}
