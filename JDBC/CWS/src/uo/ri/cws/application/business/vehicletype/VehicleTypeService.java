package uo.ri.cws.application.business.vehicletype;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;

/**
 * This service is intended to be used by the Cashier It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface VehicleTypeService {

	/**
	 * Find all available vehicle types
	 * 
	 * @return a list of VehicleTypeBLDto
	 * @throws NOT BusinessException
	 */
	List<VehicleTypeBLDto> findAllVehicleTypes() throws BusinessException;

	public static class VehicleTypeBLDto {

		public String id;
		public long version;

		public String name;
		public double pricePerHour;
		public int minTrainigHours;

	}
}
