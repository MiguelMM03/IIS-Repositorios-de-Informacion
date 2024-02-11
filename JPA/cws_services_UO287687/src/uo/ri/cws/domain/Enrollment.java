package uo.ri.cws.domain;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

public class Enrollment extends BaseEntity {
	private Mechanic mechanic;
	private Course course;
	private int attendance;
	private boolean passed;

	Enrollment() {
	}

	public Enrollment(Mechanic mechanic, Course course, int attendance,
			boolean passed) {
		ArgumentChecks.isNotNull(mechanic);
		ArgumentChecks.isNotNull(course);
		ArgumentChecks.isTrue(attendance > 0);
		ArgumentChecks.isTrue(!passed || attendance >= 85);
		this.attendance = attendance;
		this.passed = passed;
		Associations.Enroll.link(mechanic, this, course);
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public Course getCourse() {
		return course;
	}

	public int getAttendance() {
		return attendance;
	}

	public int getAttendedHoursFor(VehicleType vt) {
		int hours = 0;
		for (Dedication dedication : course.getDedications()) {
			if (dedication.getVehicleType().equals(vt)) {
				hours += dedication.getPercentage() / 100.0 * attendance / 100.0
						* course.getHours();
			}
		}
		return hours;
	}

	public boolean isPassed() {
		return passed;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	void _setCourse(Course course) {
		this.course = course;
	}

	
	

}
