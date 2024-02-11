package uo.ri.cws.application.business.workorder.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public class WorkOrderAssembler {
	public static WorkOrderDALDto toWorkOrderDALDto(WorkOrderBLDto arg) {
		WorkOrderDALDto result = new WorkOrderDALDto();
		result.id = arg.id;
		result.version = arg.version;
		result.amount = arg.amount;
		result.description = arg.description;
		result.date = arg.date;
		result.invoiceId=arg.invoiceId;
		result.mechanicId=arg.mechanicId;
		result.vehicleId=arg.vehicleId;
		result.status=arg.status;
		return result;
	}
	public static WorkOrderBLDto toWorkOrderDto(WorkOrderDALDto arg) {
		WorkOrderBLDto result = new WorkOrderBLDto();
		result.id = arg.id;
		result.version = arg.version;
		result.amount = arg.amount;
		result.description = arg.description;
		result.date = arg.date;
		result.invoiceId=arg.invoiceId;
		result.mechanicId=arg.mechanicId;
		result.vehicleId=arg.vehicleId;
		result.status=arg.status;
		return result;
	}
	public static Optional<WorkOrderBLDto> toBLDto(Optional<WorkOrderDALDto> arg) {
		Optional<WorkOrderBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toWorkOrderDto(arg.get()));
		return result;
	}
	public static List<WorkOrderBLDto> toBLDtoList(List<WorkOrderDALDto> arg){
		List<WorkOrderBLDto> result = new ArrayList<WorkOrderBLDto>();
		for (WorkOrderDALDto mr : arg)
			result.add(toWorkOrderDto(mr));
		return result;
	}

}
