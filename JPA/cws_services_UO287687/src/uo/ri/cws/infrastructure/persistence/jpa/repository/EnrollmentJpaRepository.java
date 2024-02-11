package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.util.exception.NotYetImplementedException;

public class EnrollmentJpaRepository extends BaseJpaRepository<Enrollment>
		implements EnrollmentRepository {

	@Override
	public void add(Enrollment t) {
		throw new NotYetImplementedException();

	}

	@Override
	public void remove(Enrollment t) {
		throw new NotYetImplementedException();

	}

	@Override
	public List<Enrollment> findByCourseId(String id) {
		throw new NotYetImplementedException();
	}

	@Override
	public Optional<Enrollment> findByCourseIdMechanicId(String courseId,
			String mechanicId) {
		throw new NotYetImplementedException();
	}

}
