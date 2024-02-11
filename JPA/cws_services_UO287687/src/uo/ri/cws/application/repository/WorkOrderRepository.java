package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.WorkOrder;

public interface WorkOrderRepository extends Repository<WorkOrder>{

	/**
	 * @param idsAveria, lista de los id de avería a recuperar
	 * @return lista con averias cuyo id aparece en idsAveria,
	 * 	o lista vacía si no hay ninguna
	 */
	List<WorkOrder> findByIds(List<String> workOrderIds);

	/**
	 * @param dni
	 * @return lista con averias no facturadas de un cliente
	 * 	identificado por su dni o lista vacía si no hay ninguna
	 * @return list of not invoiced work orders for the client with the 
	 * provided dni, the list might be empty
	 */
	List<WorkOrder> findNotInvoicedByDni(String dni);

	/**
	 * @return a list of all work orders either in OPEN or ASSIGNED status
	 */
	List<WorkOrder> findUnfinishedWorkOrders();

	List<WorkOrder> findWorkOrdersByVehicleId(String id);

	List<WorkOrder> findWorkOrdersByPlate(String plate);
	
	List<WorkOrder> findByClientDni(String dni);
}