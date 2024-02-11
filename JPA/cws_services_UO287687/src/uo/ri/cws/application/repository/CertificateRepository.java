package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.Certificate;

public interface CertificateRepository extends Repository<Certificate> {

	List<Certificate> findByVehicleTypeId(String id);

	List<Certificate> findSortedByVehicleTypeAndMechanic();

}
