package uo.ri.cws.application.service.workorder.impl;

import java.util.List;
import java.util.stream.Collectors;

import uo.ri.cws.application.service.workorder.WorkOrderService.WorkOrderDto;
import uo.ri.cws.domain.WorkOrder;

public class DtoAssembler {

	public static WorkOrderDto toDto(WorkOrder a) {
		WorkOrderDto dto = new WorkOrderDto();
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

	public static List<WorkOrderDto> toWorkOrderDtoList(List<WorkOrder> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

}
