package uo.ri.cws.application.persistence.vehicle;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;

public interface VehicleGateway extends Gateway<VehicleDALDto> {
	/**
	 * @param plate
	 * @return
	 */
	public Optional<VehicleDALDto> findByPlate(String plate);

	/**
	 * @param make
	 * @return
	 */
	public List<VehicleDALDto> findByMake(String make);

	/**
	 * @param arg owner's id
	 * @return List<VehicleRecord> if there is any vehicle for this client; could be
	 *         empty @
	 */
	public List<VehicleDALDto> findByClient(String arg);

	public class VehicleDALDto {

		public String id;
		public Long version;

		public String plateNumber;
		public String make;
		public String model;
		public String clientId;
		public String vehicleTypeId;
	}

}
