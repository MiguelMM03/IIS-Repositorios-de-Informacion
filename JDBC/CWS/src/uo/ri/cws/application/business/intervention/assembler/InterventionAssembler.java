package uo.ri.cws.application.business.intervention.assembler;


import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.intervention.InterventionService.InterventionBLDto;
import uo.ri.cws.application.persistence.intervention.InterventionGateway.InterventionDALDto;

public class InterventionAssembler {
	
	public static  InterventionBLDto DALDToInterventionBLDto(InterventionDALDto arg){
		InterventionBLDto value = new InterventionBLDto();
		value.id = arg.id;
		value.version = arg.version;

		value.date = arg.date;
		value.minutes = arg.minutes;
		value.mechanicId = arg.mechanicId;
		value.workorderId = arg.workorderId;
		return value;
	}
	public static List<InterventionBLDto> toDtoList(List<InterventionDALDto> arg) {
		List<InterventionBLDto> result = new ArrayList<InterventionBLDto>();
		for (InterventionDALDto mr : arg)
			result.add(DALDToInterventionBLDto(mr));
		return result;
	}
}
