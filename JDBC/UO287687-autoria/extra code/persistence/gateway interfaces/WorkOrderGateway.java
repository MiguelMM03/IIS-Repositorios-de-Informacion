package uo.ri.cws.application.persistence.workorder;

import java.time.LocalDateTime;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public interface WorkOrderGateway extends Gateway<WorkOrderDALDto> {
	List<WorkOrderDALDto> findByMechanic(String id);

	List<WorkOrderDALDto> findNotInvoicedForVehicles(List<String> vehicleIds);

	List<WorkOrderDALDto> findByVehicleId(String vehicleId);

	List<WorkOrderDALDto> findByIds(List<String> arg);

	List<WorkOrderDALDto> findByInvoice(String id);

	List<WorkOrderDALDto> findInvoiced();

	List<WorkOrderDALDto> findInvoicedByMechanic(String mechanicId);

	List<WorkOrderDALDto> findUnfinished();

	public class WorkOrderDALDto {
		public String id;
		public Long version;

		public Double amount;
		public LocalDateTime date;
		public String description;
		public String status;
		public String invoiceId;
		public String mechanicId;
		public String vehicleId;
	}

}
