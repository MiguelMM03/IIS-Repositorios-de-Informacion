package uo.ri.cws.application.business.workorder.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.CommandExecutor;
import uo.ri.cws.application.business.workorder.WorkOrderService;
import uo.ri.cws.application.business.workorder.crud.commands.AddWorkorder;
import uo.ri.cws.application.business.workorder.crud.commands.DeleteWorkorder;
import uo.ri.cws.application.business.workorder.crud.commands.FindUnfinishedWorkOrders;
import uo.ri.cws.application.business.workorder.crud.commands.FindWorkOrdersByPlateNumber;
import uo.ri.cws.application.business.workorder.crud.commands.FindWorkOrderById;
import uo.ri.cws.application.business.workorder.crud.commands.UpdateWorkOrder;
import uo.ri.cws.application.business.workorder.crud.commands.AssignWorkOrderToMechanic;

public class WorkOrderServiceImpl implements WorkOrderService {

	CommandExecutor c = new CommandExecutor();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WorkOrderBLDto registerNew(WorkOrderBLDto dto) throws BusinessException {
		return c.execute(new AddWorkorder(dto));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateWorkOrder(WorkOrderBLDto dto) throws BusinessException {
		c.execute(new UpdateWorkOrder(dto));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteWorkOrder(String id) throws BusinessException {
		c.execute(new DeleteWorkorder(id));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<WorkOrderBLDto> findWorkOrderById(String woId) throws BusinessException {
		return c.execute(new FindWorkOrderById(woId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WorkOrderBLDto> findUnfinishedWorkOrders() throws BusinessException {
		return c.execute(new FindUnfinishedWorkOrders());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WorkOrderBLDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		return c.execute(new FindWorkOrdersByPlateNumber(plate));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void assignWorkOrderToMechanic(String woID, String mechanicDni) throws BusinessException {
		c.execute(new AssignWorkOrderToMechanic(woID, mechanicDni));

	}

}
