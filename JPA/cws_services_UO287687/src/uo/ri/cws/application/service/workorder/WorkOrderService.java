package uo.ri.cws.application.service.workorder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Foreman
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface WorkOrderService {

	/**
	 * Registers a new work order out of the data received. Only this fields
	 * will be taken into account:
	 * 	- the vehicle id, and
	 * 	- the description of the work to be done
	 * The rest of fields will assigned by the service, thus any provided value
	 * will be ignored.
	 *
	 * @param dto. Just vehicle id and description.
	 *
	 * @return another dto with the provided values and service-assigned
	 * 		fields filled: id, LocalDate and status
	 *
	 * @throws BusinessException if:
	 * 	- there is another work order for the same vehicle at the same
	 * 		LocalDate and time (timestamp), or
	 *  - the vehicle does not exist
	 */
	WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException;

	/**
	 * Updates the description of the work order specified by the id and
	 * 	version fields. No other field is taken for the update. Only work orders
	 * 	with status of OPEN or ASSIGNED can be updated.
	 *
	 * @param dto, with work order id, version and description.
	 *
	 * @throws BusinessException if:
	 * 	- there is no work order with that id, or
	 *  - the work order has not the specified version (optimistic lock), or
	 *  - the work order is not in the OPEN or ASSIGNED status
	 */
	void updateWorkOrder(WorkOrderDto dto) throws BusinessException;

	/**
	 * Removes the work order form the system if it still do not have
	 * 	interventions.
	 *
	 * @param id, of the work order
	 *
	 * @throws BusinessException if:
	 * 	- the work order does not exist, or
	 *  - there already is some intervention registered.
	 */
	void deleteWorkOrder(String id) throws BusinessException;

	/**
	 * @param woId, id of the work order
	 * @return the optional filled if the work order exists
	 * @throws BusinessException, DOES NOT
	 */
	Optional<WorkOrderDto> findWorkOrderById(String woId)
			throws BusinessException;

	/**
	 * Returns a list of work orders registered for the specified vehicle id
	 * @param id
	 * @return the list, will be empty if:
	 * 	- the vehicle does not exist, or
	 *  - it has no work orders registered.
	 * @throws BusinessException, DOES NOT
	 */
	List<WorkOrderDto> findWorkOrdersByVehicleId(String id)
			throws BusinessException;

	/**
	 * Returns a list of work orders registered for the specified plate number
	 * @param plate
	 * @return the list, will be empty if:
	 * 	- the vehicle does not exist, or
	 *  - it has no work orders registered.
	 * @throws BusinessException, DOES NOT
	 */
	List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate)
			throws BusinessException;

	List<WorkOrderDto> findWorkOrdersByClientDni(String dni)
			throws BusinessException;

	/**
	 * Assigns the work order to mechanic so he/she can see what
	 * 	work has to today/next. Only work orders with OPEN status can be assigned.
	 * 	Work orders changes to ASSIGNED status when assigned.
	 *
	 * @param woId, the work order id
	 * @param mDni, the mechanic dni
	 *
	 * @throws BusinessException if:
	 * 	- the mechanic does not exist, or
	 *  - the work order does not exist, or
	 *  - the work order is not in OPEN status
	 */
	void assignWorkOrderToMechanic(String woId, String mDni)
			throws BusinessException;

	/**
	 * @return a list of all work orders either in the OPEN or ASSIGN status
	 * @throws BusinessException, DOES NOT
	 */
	List<WorkOrderDto> findUnfinishedWorkOrders() throws BusinessException;

	public class WorkOrderDto {
		public String id;
		public long version;

		public String vehicleId;
		public String description;
		public LocalDateTime date;
		public double amount;
		public String state;

		// might be null
		public String mechanicId;
		public String invoiceId;
	}
	
}
