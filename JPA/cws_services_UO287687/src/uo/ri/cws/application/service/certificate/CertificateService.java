package uo.ri.cws.application.service.certificate;

import java.time.LocalDate;
import java.util.List;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface CertificateService {
	
	/**
	 * Generates certificates according to the rules:
	 * 	- Each vehicle type specifies the number of attended-and-passed 
	 * 		training hours needed to get the certificate for that vehicle type
	 * 
	 * 	- The mechanic has to accumulate at least that number of hours in 
	 * 		one or several courses
	 * 
	 *  - A course specifies the % of training hours devoted to some vehicle types
	 *  
	 * @return the number of new certificates generated
	 * 
	 * DOES NOT @throws BusinessException
	 */
	int generateCertificates() throws BusinessException;

	/**
	 * Returns a list of certificates (i.e, certified mechanics) for the
	 * 	vehicle type. Every certificate includes full mechanic data
	 * 	(@see MechanicForCertficationDto).
	 *
	 * @param id of the vehicle type
	 *
	 * @return the list. It might be empty if no mechanic is certified for the
	 * 	specified vehicle type.
	 * 
	 * @throws BusinessException, DOES NOT
	 */
	List<CertificateDto> findCertificatesByVehicleTypeId(String id)
			throws BusinessException;
	
	/**
	 * Returns a SORTED list of certificates (i.e, certified mechanics). 
	 * Every certificate includes full mechanic data (@see MechanicForCertificationDto).
	 *
	 * @return a SORTED list. It might be empty if no mechanic is certified for any 
	 * 	of the vehicle types
	 * 
	 * Sorting: The list is sorted by vehicle type name first, and then by the 
	 * 	mechanic's surname and name
	 * 
	 * @throws BusinessException DOES NOT
	 */
	List<CertificateDto> findCertificatesSortedByVehicleType() throws BusinessException;
	
	public class CertificateDto {
		public String id;
		public long version;
		
		public LocalDate obtainedAt;
		public MechanicForCertificationDto mechanic = new MechanicForCertificationDto();
		public VehicleTypeForCertificationDto vehicleType = new VehicleTypeForCertificationDto();
	}

	public class VehicleTypeForCertificationDto {
		public String id;
		public long version;
		
		public String name;
		public double pricePerHour;
		public int minTrainigHours;
	}

	public class MechanicForCertificationDto {
		public String id;
		public long version;
		
		public String dni;
		public String name;
		public String surname;
	}

}
