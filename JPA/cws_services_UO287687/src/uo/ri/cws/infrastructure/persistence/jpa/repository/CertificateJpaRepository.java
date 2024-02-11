package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;

import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;
import uo.ri.util.exception.NotYetImplementedException;

public class CertificateJpaRepository extends BaseJpaRepository<Certificate>
		implements CertificateRepository {

	@Override
	public List<Certificate> findByVehicleTypeId(String id) {
		return Jpa.getManager()
				.createNamedQuery("Certificate.findByVehicleTypeId",
						Certificate.class)
				.setParameter(1, id).getResultList();
	}

	@Override
	public List<Certificate> findSortedByVehicleTypeAndMechanic() {
		throw new NotYetImplementedException();
	}

}
