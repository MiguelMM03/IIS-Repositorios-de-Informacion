package uo.ri.cws.application.business.certificate;

import java.time.LocalDate;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;

/**
 * This service is intended to be used by the Manager It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface CertificateService {
	
	 /**
     * Prueba de autoría PL1
     * 
     * Devuelve una lista de certificados expedidos antes de una fecha
     * y para un mecánico concreto.
     * 
     * @param dni El dni del mecánico
     * @param year El año
     * @param month El mes
     * @param day El día
     *
     * @return la lista, que puede estar vacía si no se ha certificado el mecánico
     * @throws BusinessException cuando el mecánico no existe
     * @throws IllegalArgumentException cuando el dni es null
     * 									cuando el año es negativo
     * 									cuando el mes no está entre 1 y 12
     * 									cuando el día no está entre 1 y 31
     * 						
     */
    List<CertificateBLDto> findCertificatesBeforeYearByMechanicDni(String dni, int year, int month, int day)
	    throws BusinessException;
    
    /**
     * Generates certificates according to the rules: - Each vehicle type
     * specifies the number of attended-and-passed training hours needed to get
     * the certificate for that vehicle type
     * 
     * - The mechanic has to accumulate at least that number of hours in one or
     * several courses
     * 
     * - A course specifies the % of training hours devoted to some vehicle
     * types
     * 
     * If the mechanic already has a certification for this vehicle type, the new
     * one will be ignored 
     * @return the number of new certificates generated
     * 
     * @throws BusinessException DOES NOT
     */
    int generateCertificates() throws BusinessException;

    /**
     * Returns a list of certificates (i.e, certified mechanics) for the vehicle
     * type. Every certificate includes full mechanic data (@see MechanicBLDto).
     *
     * @param id of the vehicle type
     *
     * @return the list. It might be empty if no mechanic is certified for the
     *         specified vehicle type.
     * @throws IllegalArgumentException if the argument is null or empty
     * @throws BusinessException DOES NOT
     */
    List<CertificateBLDto> findCertificatesByVehicleTypeId(String vehicleTypeId)
	    throws BusinessException;

    /**
     * Returns a list of rows, one for each mechanic that had been certified,
     * sorted by vehicle type and mechanic.
     *
     * @return the list, that might be empty if no mechanic has been certified
     * @throws BusinessException DOES NOT
     */
    List<CertificateBLDto> findCertificatesByVehicleTypeId()
	    throws BusinessException;
    
   

    public class CertificateBLDto {

	public String id;
	public Long version;

	public MechanicBLDto mechanic;
	public VehicleTypeBLDto vehicleType;
	public LocalDate obtainedAt;

    }

}
