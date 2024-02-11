package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.domain.Course;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.util.exception.NotYetImplementedException;

public class CourseJpaRepository extends BaseJpaRepository<Course>
		implements CourseRepository {

	@Override
	public Optional<Course> findByCode(String code) {
		throw new NotYetImplementedException();
	}

	@Override
	public List<Course> findAllActive() {
		throw new NotYetImplementedException();
	}

}
