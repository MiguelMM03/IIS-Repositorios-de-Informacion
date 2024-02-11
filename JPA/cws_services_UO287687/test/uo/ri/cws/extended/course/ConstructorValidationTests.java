package uo.ri.cws.extended.course;

import java.time.LocalDate;

import org.junit.Test;

import uo.ri.cws.domain.Course;

public class ConstructorValidationTests {

	private String code = "code";
	private int duration = 10;
	private String name = "name";
	private LocalDate startDate = LocalDate.now().plusDays( 15 );
	private LocalDate endDate = startDate.plusDays( 5 );
	private String description = "description";

	/**
	 * code cannot be empty (nor null)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCodeNotEmpty() {
		new Course("", name, description, startDate, endDate, duration);
	}

	/**
	 * name cannot be empty (nor null)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNameNotEmpty() {
		new Course(code, "", description, startDate, endDate, duration);
	}

	/**
	 * description cannot be empty (nor null)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDescriptionNotEmpty() {
		new Course(code, name, "", startDate, endDate, duration);
	}

	/**
	 * startDate cannot be null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStartDateNotNull() {
		new Course(code, name, description, null, endDate, duration);
	}

	/**
	 * endDate cannot be null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEndDateNotNull() {
		new Course(code, name, description, startDate, null, duration);
	}

	/**
	 * endDate must be after startDate
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEndDateAfterStartDate() {
		startDate = LocalDate.now();
		endDate = startDate.minusDays( 1 );
		
		new Course(code, name, description, startDate, endDate, duration);
	}

	/**
	 * duration cannot be negative 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDurationNonZero() {
		new Course(code, name, description, startDate, endDate, -1);
	}

	/**
	 * duration cannot be negative
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDurationnonNegative() {
		new Course(code, name, description, startDate, endDate, -1);
	}

}
