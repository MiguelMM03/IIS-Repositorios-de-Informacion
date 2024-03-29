package uo.ri.cws.application.service.mechanic.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.impl.commands.AddMechanic;
import uo.ri.cws.application.service.mechanic.impl.commands.DeleteMechanic;
import uo.ri.cws.application.service.mechanic.impl.commands.FindAllMechanics;
import uo.ri.cws.application.service.mechanic.impl.commands.FindMechanicByDni;
import uo.ri.cws.application.service.mechanic.impl.commands.FindMechanicById;
import uo.ri.cws.application.service.mechanic.impl.commands.UpdateMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

public class MechanicCrudServiceImpl implements MechanicCrudService {
	
	private CommandExecutor executor =Factory.executor.forExecutor();

	@Override
	public MechanicDto addMechanic(MechanicDto dto) throws BusinessException {
		return executor.execute(new AddMechanic( dto ));
	}

	@Override
	public void updateMechanic(MechanicDto dto) throws BusinessException {
		executor.execute(new UpdateMechanic(dto));
	}

	@Override
	public void deleteMechanic(String iddto) throws BusinessException {
		executor.execute(new DeleteMechanic(iddto));
	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		return executor.execute(new FindAllMechanics());
	}

	@Override
	public Optional<MechanicDto> findMechanicById(String id) throws BusinessException {
		return executor.execute(new FindMechanicById(id));
	}

	@Override
	public Optional<MechanicDto> findMechanicByDni(String dni)
			throws BusinessException {
		return executor.execute(new FindMechanicByDni(dni));
	}

}