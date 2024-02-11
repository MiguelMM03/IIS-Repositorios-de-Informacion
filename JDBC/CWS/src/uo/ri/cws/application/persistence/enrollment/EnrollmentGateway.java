package uo.ri.cws.application.persistence.enrollment;

import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.course.CourseGateway.CourseDALDto;
import uo.ri.cws.application.persistence.enrollment.EnrollmentGateway.EnrollmentDALDto;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;;

public interface EnrollmentGateway extends Gateway<EnrollmentDALDto> {

	/**
	 * Find mechanics who passed the given course
	 * @param courseId Course id
	 * @return A list of mechanics
	 */
	List<MechanicDALDto> findMechanicWithPassedCourse(String courseId);

	/**
	 * Find courses passed by a mechanic
	 * @param mechanicId Mechanic id
	 * @return A list of courses
	 */
	List<CourseDALDto> findCoursesPassedByMechanic(String mechanicId);

	/**
	 * Find courses by mechanic
	 * @param mechanicId Mechanic id
	 * @return A list of courses
	 */
	List<CourseDALDto> findCoursesByMechanic(String mechanicId);

	/**
	 * Find the number of hours attended to a course by a mechanic
	 * @param courseId Course id
	 * @param mechanicId Mechanic id
	 * @return The number of hours
	 */
	int findAttendanceByCourseAndMechanic(String courseId, String mechanicId);

	public class EnrollmentDALDto {

		public String id;
		public Long version;

		public String mechanicId;
		public String courseId;
		public int attendance; // percentage 0..100
		public boolean passed;

		// Just for listing purposes
		public MechanicDALDto mechanic;
		public CourseDALDto course;

	}
}
