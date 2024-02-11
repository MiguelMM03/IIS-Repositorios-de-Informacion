package uo.ri.cws.application.persistence.intervention;

import java.time.LocalDateTime;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.intervention.InterventionGateway.InterventionDALDto;

public interface InterventionGateway extends Gateway<InterventionDALDto>{
	
	/**
	 * Find interventions by the workorder given
	 * @param workOrderId The workorder
	 * @return A list of interventions
	 */
	List<InterventionDALDto> findInterventionsByWorkOrderId(String workOrderId);
	public class InterventionDALDto{
		public String id;
		public long version;

		public LocalDateTime date;
		public int minutes;

		// might be null
		public String mechanicId;
		public String workorderId;

	}

}
