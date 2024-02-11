package uo.ri.cws.application.service.course;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface CourseCrudService {
	
	/**
	 * Registers a new course in the system
	 * 
	 * @param dto, it must specify: name, description, startLocalDate, endLocalDate, hours
	 * 		and the table of percentages. 
	 * 		The id and the version fields must be null (will be assigned by the system).
	 * 
	 * @return the same Dto with the id field assigned to the created UUID
	 * 
	 * @throws BusinessException, if:
	 *  	- any field other than id and version is null or empty, or
	 * 		- there already exists a course with the same name, or
	 * 		- there is percentage devoted to a non existing vehicle type, or
	 * 		- the initial and final LocalDates are in the past or inverted, or
	 * 		- the number of hours are zero or negative, or
	 *  	- there are no dedications specified, or
	 *  	- the sum of devoted percentages does not equals 100%, or
	 *  	- the are any dedication with an invalid percentage (empty, zero, negative)	
	 */
	CourseDto registerNew(CourseDto dto) throws BusinessException;
	
	/**
	 * Updates the course specified by the id with the new data. 
	 * A course can only be modified if it has not yet started. 
	 * 
	 * 		Note: If the update is motivated by a wrong start date that 
	 * has been specified in the past, when it should be in the future,
	 * then the unique possible option for the user is to delete the course
	 * and start again.
	 * 
	 * @param dto. Must specify all the fields. The id and version fields must 
	 * 	match the current state of the course. All the rest of fields must be 
	 * 	filled, even if there is no change in the data. So it must pass the same
	 * 	validation as for new courses. 
	 * 
	 * @throws BusinessException if:
	 * 	- it does not exist the course with the specified id, or
	 *  - the current version of the course does not match the version of the dto, or
	 *  - the course has its start date in the past, or 
	 * 	- the new data does not pass the validation specified 
	 * 		in @see registerNew
	 */
	void updateCourse(CourseDto dto) throws BusinessException;

	/**
	 * A course can only be deleted if it still has no attendance registered.
	 * Delete a course also implies remove all its dedications in cascade.
	 * 
	 * Note: A course and its dedications form an aggregate.
	 *  
	 * @param id
	 * @throws BusinessException if:
	 * 	- there is no course with the specified id, or
	 * 	- the course already has enrollments registered.
	 */
	void deleteCourse(String id) throws BusinessException;
	
	/**
	 * 
	 * @return a list of CourseDto. Each one represents a course. 
	 * @see CourseDto class for details.
	 * 
	 * DOES NOT @throws BusinessException
	 */
	List<CourseDto> findAllCourses() throws BusinessException;
	
	/**
	 * List all courses that have already started but not yet been finished
	 * 
	 * @return a list of CourseBLDto. Each one represents an active course.
	 * @throws BusinessException DOES NOT
	 */
	List<CourseDto> findAllActiveCourses() throws BusinessException;

	/**
	 * @param cId
	 * @return an Optional, what if there is no course with the specified id?
	 * DOES NOT @throws BusinessException
	 * @throws BusinessException 
	 */
	Optional<CourseDto> findCourseById(String cId) throws BusinessException;
	
	/**
	 * Returns a report summary of the training of the specified mechanic, 
	 * considering just finished courses.
	 *
	 * @param id of the mechanic
	 *
	 * @return the list of lines, one for each vehicle type the mechanic have
	 *         had some training
	 *
	 *  @throws 
	 * 		IllegalArgumentException, if mechanicId is null or empty
	 */
	List<TrainingForMechanicReportLineDto> findTrainingHoursByMechanicId(String mechanicId)
	    throws BusinessException;

	/**
	 * Returns a list of rows, one for each vehicle type and mechanic that had 
	 * attended to a course.
	 *
	 * @return the list, that might be empty if no mechanic has been trained
	 * 		for any vehicle type.
	 * @throws BusinessException, DOES NOT
	 */
	List<TrainingForVehicleTypeReportLineDto> findTrainingSortedByVehicleTypeAndMechanic()
			throws BusinessException;

	public class CourseDto {

		public String id;
		public long version;
		
		public String code;
		public String name;
		public String description;
		public LocalDate startDate;
		public LocalDate endDate;
		public int hours;
		
		/**
		 * A map of the form:
		 * 	Vehicle type id -> percentage
		 * 	 asd-234-sdc -> 25%
		 * 	 fcd-346-tyc -> 25%
		 * 	 cdy-469-ycf -> 50%
		 */
		public Map<String, Integer> percentages = new HashMap<>();
	}

	public class TrainingForMechanicReportLineDto {
	
		public String vehicleTypeName;
		public int enrolledHours;
		public int attendedHours;
	
	}

	public class TrainingForVehicleTypeReportLineDto {
	
		public String vehicleTypeName;
		public String mechanicFullName;
		public int enrolledHours;
	
	}

}
