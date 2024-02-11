package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;

import uo.ri.cws.application.repository.TrainingReporter;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;

public class TrainingJpaReporter extends BaseJpaRepository<Void>
		implements TrainingReporter {

	@Override
	public List<Object[]> queryTrainingByVehicleTypeForMechanicId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> queryTrainingByVehicleType() {
		// TODO Auto-generated method stub
		return null;
	}

}
