package uo.ri.cws.application.repository;

import java.util.List;

public interface TrainingReporter {

	/**
	 * @param id
	 * @return a list of arrays. Each one of the form:
	 * 	[0] -> vehicle type name (String),
	 * 	[1] -> amount enrolled hours (long)
	 * 	[2]	-> amount attended hours (long)
	 */
	List<Object[]> queryTrainingByVehicleTypeForMechanicId(String id);

	/**
	 * @param id
	 * @return a list of arrays. Each one of the form:
	 * 	[0] -> vehicle type name (String),
	 * 	[1] -> mechanic name (String),
	 * 	[2] -> amount enrolled hours (long)
	 */
	List<Object[]> queryTrainingByVehicleType();

}
