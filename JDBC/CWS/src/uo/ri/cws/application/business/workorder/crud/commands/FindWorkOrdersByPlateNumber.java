package uo.ri.cws.application.business.workorder.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.vehicle.assembler.VehicleAssembler;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.business.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import util.assertion.Argument;

public class FindWorkOrdersByPlateNumber implements Command<List<WorkOrderBLDto>>{

	private String plate;
	private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();
	private VehicleGateway vg=PersistenceFactory.forVehicle();
	
	public FindWorkOrdersByPlateNumber(String plate) {
		Argument.isNotEmpty(plate);
		this.plate=plate;
		
	}
	@Override
	public List<WorkOrderBLDto> execute() throws BusinessException {
		List<WorkOrderBLDto> list=new ArrayList<WorkOrderBLDto>();
		Optional<VehicleBLDto> v=VehicleAssembler.toVehicleDto(vg.findByPlate(plate));
		if(v.isPresent()) {
			list.addAll(WorkOrderAssembler.toBLDtoList(wog.findByVehicleId(v.get().id)));
		}
		return list;
	}

}
