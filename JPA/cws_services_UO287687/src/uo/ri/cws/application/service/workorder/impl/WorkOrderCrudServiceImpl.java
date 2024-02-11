package uo.ri.cws.application.service.workorder.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderService;
import uo.ri.cws.application.service.workorder.impl.commands.AddWorkOrder;
import uo.ri.cws.application.service.workorder.impl.commands.AssignWorkOrder;
import uo.ri.cws.application.service.workorder.impl.commands.FindUnfinishedWorkOrders;
import uo.ri.cws.application.service.workorder.impl.commands.FindWorkOrderById;
import uo.ri.cws.application.service.workorder.impl.commands.FindWorkOrdersByClientDni;
import uo.ri.cws.application.service.workorder.impl.commands.FindWorkOrdersByPlateNumber;
import uo.ri.cws.application.service.workorder.impl.commands.FindWorkOrdersByVehicleId;
import uo.ri.cws.application.service.workorder.impl.commands.RemoveWorkOrder;
import uo.ri.cws.application.service.workorder.impl.commands.UpdateWorkOrder;
import uo.ri.cws.application.util.command.CommandExecutor;

public class WorkOrderCrudServiceImpl implements WorkOrderService{

	private CommandExecutor executor =Factory.executor.forExecutor();
	/**
	 * {inherit}
	 */
	@Override
	public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {
		return executor.execute(new AddWorkOrder(dto));
	}

	/**
	 * {inherit}
	 */
	@Override
	public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
		executor.execute(new UpdateWorkOrder(dto));
		
	}

	/**
	 * {inherit}
	 */
	@Override
	public void deleteWorkOrder(String id) throws BusinessException {
		executor.execute(new RemoveWorkOrder(id));
		
	}

	/**
	 * {inherit}
	 */
	@Override
	public Optional<WorkOrderDto> findWorkOrderById(String woId)
			throws BusinessException {
		return executor.execute(new FindWorkOrderById(woId));
	}

	/**
	 * {inherit}
	 */
	@Override
	public List<WorkOrderDto> findWorkOrdersByVehicleId(String id)
			throws BusinessException {
		return executor.execute(new FindWorkOrdersByVehicleId(id));
	}

	/**
	 * {inherit}
	 */
	@Override
	public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate)
			throws BusinessException {
		return executor.execute(new FindWorkOrdersByPlateNumber(plate));
	}

	/**
	 * {inherit}
	 */
	@Override
	public List<WorkOrderDto> findWorkOrdersByClientDni(String dni)
			throws BusinessException {
		return executor.execute(new FindWorkOrdersByClientDni(dni));
	}

	/**
	 * {inherit}
	 */
	@Override
	public void assignWorkOrderToMechanic(String woId, String mDni)
			throws BusinessException {
		executor.execute(new AssignWorkOrder(woId,mDni));
		
	}

	/**
	 * {inherit}
	 */
	@Override
	public List<WorkOrderDto> findUnfinishedWorkOrders()
			throws BusinessException {
		return executor.execute(new FindUnfinishedWorkOrders());
	}

}
