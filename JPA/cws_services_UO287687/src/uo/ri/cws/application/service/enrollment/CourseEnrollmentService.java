package uo.ri.cws.application.service.enrollment;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Manager It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface CourseEnrollmentService {

	/**
	 * Registers the attendance of a mechanic to a course.
	 * 
	 * @param dto, with this fields filled: mechanicId, courseId, attendance and
	 *             the boolean passed.
	 *
	 * @return the same dto but with the id field set to the generated UUID
	 *         value
	 *
	 * @throws BusinessException if: 
	 * 	- the mechanic id does not exit, or 
	 * 	- the course id does not exist, or 
	 *  - there already is another enrollment registered for the same
	 *        mechanic and course, or 
	 *  - the attendance is under 85% and the course is marked as passed, or 
	 *  - the course is not yet finished, or <- IGNORE THIS, complicates testing 
	 *  - the value for percentage is not in the range 0..100
	 */
	EnrollmentDto registerNew(EnrollmentDto dto) throws BusinessException;

	/**
	 * Removes the attendance record specified by the id
	 * 
	 * @param id of the attendance record
	 * @throws BusinessException if the attendance record does not exist
	 */
	void deleteEnrollment(String id) throws BusinessException;

	/**
     * Lists all the attendance records for the specified course id.
     * 
     * @return the list of attendances or an empty list if the course does not
     *         exist or the course has no attendance registered yet
     *         
     * @throws IllegalArgumetException if the arg is null or empty 
     * 			BusinessException DOES NOT
     */
    List<EnrollmentDto> findEnrollmentByCourseId(String id) throws BusinessException;
    
    public class EnrollmentDto {
		public String id;
		public long version;

		public String mechanicId;
		public String courseId;
		public int attendance; // percentage 0..100
		public boolean passed;

		// Just for listing purposes
		public MechanicForEnrollmentDto mechanic;
		public CourseForEnrollmentDto course;
	}

	public class MechanicForEnrollmentDto {
		public String id;
		public long version;

		public String dni;
		public String name;
		public String surname;
	}

	public class CourseForEnrollmentDto {
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
		 * 		Vehicle type id -> percentage 
		 * 		asd-234-sdc -> 25%
		 * 		fcd-346-tyc -> 25% 
		 * 		cdy-469-ycf -> 50%
		 */
		public Map<String, Integer> percentages = new HashMap<>();
	}
}
