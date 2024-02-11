package uo.ri.cws.application.persistence.dedication;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.dedication.DedicationGateway.DedicationDALDto;

public interface DedicationGateway extends Gateway<DedicationDALDto>{

	/**
	 * Find the percentage of the course for a vehicle type
	 * @param vehicleTypeId Vehicle type
	 * @param course_id Course id
	 * @return the percentage
	 */
	int findPercentageForVehicleTypeInCourse(String vehicleTypeId, String course_id);
	public class DedicationDALDto {
		public String id;
		public int percentage;
		public long version;
		public String courseId;
		public String vehicleTypeId;
	}
}
