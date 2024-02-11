package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;

import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class WorkOrderJpaRepository extends BaseJpaRepository<WorkOrder>
		implements WorkOrderRepository {

	@Override
	public List<WorkOrder> findByIds(List<String> idsAveria) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findByIds", WorkOrder.class)
				.setParameter(1, idsAveria).getResultList();
	}

	@Override
	public List<WorkOrder> findNotInvoicedByDni(String dni) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findNotInvoicedByDni",
						WorkOrder.class)
				.setParameter(1, dni).getResultList();
	}

	@Override
	public List<WorkOrder> findUnfinishedWorkOrders() {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findUnfinished",
						WorkOrder.class)
				.getResultList();
	}

	@Override
	public List<WorkOrder> findWorkOrdersByVehicleId(String id) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findByVehicleId", WorkOrder.class)
				.setParameter(1, id).getResultList();
	}

	@Override
	public List<WorkOrder> findWorkOrdersByPlate(String plate) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findByPlate", WorkOrder.class)
				.setParameter(1, plate).getResultList();
	}
	
	@Override
	public List<WorkOrder> findByClientDni(String dni) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findByClientDni",
						WorkOrder.class)
				.setParameter(1, dni).getResultList();
	}

}
