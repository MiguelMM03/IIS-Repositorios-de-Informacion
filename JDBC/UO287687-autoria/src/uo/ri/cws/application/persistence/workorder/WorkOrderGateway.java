package uo.ri.cws.application.persistence.workorder;

import java.time.LocalDateTime;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public interface WorkOrderGateway extends Gateway<WorkOrderDALDto> {
	/**
	 * Find the workorders by the mechanic id
	 * @param id Mechanic id
	 * @return A list of workorders
	 */
	List<WorkOrderDALDto> findByMechanic(String id);

	/**
	 * Find not invoiced workorders of the given vehicles
	 * @param vehicleIds Vehicles ids
	 * @return A list of workorders
	 */
	List<WorkOrderDALDto> findNotInvoicedForVehicles(List<String> vehicleIds);

	/**
	 * Find workorders of the given vehicle
	 * @param vehicleId Vehicle id
	 * @return A list of workorders
	 */
	List<WorkOrderDALDto> findByVehicleId(String vehicleId);

	/**
	 * Find workorders by their ids
	 * @param arg Ids of the workorders
	 * @return A list of workorders
	 */
	List<WorkOrderDALDto> findByIds(List<String> arg);

	/**
	 * Find workorders by invoice
	 * @param id Invoice id
	 * @return A list of workorders
	 */
	List<WorkOrderDALDto> findByInvoice(String id);

	/**
	 * Find invoiced workorders
	 * @return A list of workorders
	 */
	List<WorkOrderDALDto> findInvoiced();

	/**
	 * Find invoiced workorders by mechanic
	 * @param mechanicId Mechanic id
	 * @return A list of workorders
	 */
	List<WorkOrderDALDto> findInvoicedByMechanic(String mechanicId);

	/**
	 * Find unfinished workorders
	 * @return A list of workorders
	 */
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
