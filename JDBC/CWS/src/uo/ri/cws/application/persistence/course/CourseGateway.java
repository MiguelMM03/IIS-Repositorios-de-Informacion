package uo.ri.cws.application.persistence.course;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.course.CourseGateway.CourseDALDto;;

public interface CourseGateway extends Gateway<CourseDALDto> {
	public class CourseDALDto {
		public String id;
		public Long version;

		public String code;
		public String name;
		public String description;
		public LocalDate startDate;
		public LocalDate endDate;
		public int hours;

		/**
		 * A map of the form: Vehicle type id -> percentage 2423 -> 25%
		 * 4232-346-tyc -> 25% 5445 -> 50%
		 */
		public Map<String, Integer> percentages = new HashMap<>();
	}
	
	/**
	 * Find all the finished courses
	 * @return A List of courses
	 */
	List<CourseDALDto> findAllFinishedCourses();
}
