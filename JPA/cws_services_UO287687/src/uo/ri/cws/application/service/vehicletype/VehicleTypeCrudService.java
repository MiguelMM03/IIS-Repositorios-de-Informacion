package uo.ri.cws.application.service.vehicletype;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Cashier
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface VehicleTypeCrudService {

	/**
	 * @return a list of VehicleTypeDto. 
	 * @see VehicleTypeDto class for details.
	 * 
	 * DOES NOT @throws BusinessException
	 */
	List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException;
	
	public class VehicleTypeDto {

		public String id;
		public long version;
		
		public String name;
		public double pricePerHour;
		public int minTrainigHours;
		
	}

}
