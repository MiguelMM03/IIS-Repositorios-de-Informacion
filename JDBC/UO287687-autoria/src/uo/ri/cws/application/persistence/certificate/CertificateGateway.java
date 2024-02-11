package uo.ri.cws.application.persistence.certificate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.certificate.CertificateGateway.CertificateDALDto;

public interface CertificateGateway extends Gateway<CertificateDALDto> {
	/**
	 * Find certificates of the given vehicle type
	 * @param id Vehicle type id
	 * @return A list of certificates
	 */
	List<CertificateDALDto> findCertificatesByVehicleTypeId(String id);

	/**
	 * Find one or no certificate by given parameters
	 * @param vehicleTypeId Vehicle type id
	 * @param mechanicId Mechanic id
	 * @return One or no certificate
	 */
	Optional<CertificateDALDto> findCertificatesByVehicleTypeIdAndMechanic(String vehicleTypeId, String mechanicId);
	
	List<CertificateDALDto> findCertificatesBeforeYearByMechanic(LocalDate date, String MechanicDni);

	public class CertificateDALDto {

		public String id;
		public Long version;

		public String mechanicId;
		public String vehicleTypeId;
		public LocalDate date;

	}
}
