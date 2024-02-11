package uo.ri.cws.application.business.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.commands.AddMechanic;
import uo.ri.cws.application.business.mechanic.crud.commands.DeleteMechanic;
import uo.ri.cws.application.business.mechanic.crud.commands.FindAllMechanic;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicByDni;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicById;
import uo.ri.cws.application.business.mechanic.crud.commands.UpdateMechanic;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class MechanicServiceImpl implements MechanicService {

	CommandExecutor c = new CommandExecutor();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MechanicBLDto addMechanic(MechanicBLDto mechanic) throws BusinessException {
		return c.execute(new AddMechanic(mechanic));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteMechanic(String idMechanic) throws BusinessException {
		c.execute(new DeleteMechanic(idMechanic));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateMechanic(MechanicBLDto mechanic) throws BusinessException {
		c.execute(new UpdateMechanic(mechanic));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicById(String idMechanic) throws BusinessException {
		return c.execute(new FindMechanicById(idMechanic));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicByDni(String dniMechanic) throws BusinessException {
		return c.execute(new FindMechanicByDni(dniMechanic));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MechanicBLDto> findAllMechanics() throws BusinessException {
		return c.execute(new FindAllMechanic());
	}

}
