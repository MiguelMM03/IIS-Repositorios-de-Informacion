package uo.ri.cws.application.business.enrollment;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;

public interface EnrollmentService {
	/**
	 * Registers the attendance of a mechanic to a course.
	 * 
	 * @param dto, with this fields filled: mechanicId, courseId, attendance and the
	 *             boolean passed.
	 *
	 * @return the same dto but with the id field set to the generated UUID value
	 *
	 * @throws IllegalArgumentException if: - the argument is null or if any of the
	 *                                  Strings in the argument is null or empty.
	 *                                  BusinessException if: - the mechanic id does
	 *                                  not exit, or - the course id does not exist,
	 *                                  or - there already is another enrollment
	 *                                  registered for the same mechanic and course,
	 *                                  or - the attendance is under 85% and the
	 *                                  course is marked as passed, or - the course
	 *                                  is not yet finished, or the value for
	 *                                  percentage is not in the range 0..100
	 */
	EnrollmentBLDto registerNew(EnrollmentBLDto dto) throws BusinessException;

	/**
	 * Removes the attendance record specified by the id
	 * 
	 * @param id of the attendance record
	 * @throws IllegalArgumetException if the arg is null or empty
	 * @throws BusinessException       if the attendance record does not exist
	 */
	void deleteEnrollment(String id) throws BusinessException;

	/**
	 * Lists all the attendance records for the specified course id.
	 * 
	 * @return the list of attendances or an empty list if the course does not exist
	 *         or the course has no attendance registered yet
	 * @throws IllegalArgumetException if the arg is null or empty BusinessException
	 *                                 DOES NOT
	 */
	List<EnrollmentBLDto> findEnrollmentByCourseId(String id) throws BusinessException;

	public class EnrollmentBLDto {

		public String id;
		public Long version;

		public String mechanicId;
		public String courseId;
		public int attendance; // percentage 0..100
		public boolean passed;

		// Just for listing purposes
		public MechanicBLDto mechanic;
		public CourseBLDto course;

	}

}
