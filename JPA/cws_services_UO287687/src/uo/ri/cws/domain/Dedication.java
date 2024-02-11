package uo.ri.cws.domain;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

public class Dedication extends BaseEntity{
	private int percentage;
	private VehicleType vehicleType;
	private Course course;
	
	Dedication() {}
	Dedication(Course course,VehicleType vt, int percentage) {
		ArgumentChecks.isNotNull(course);
		ArgumentChecks.isNotNull(vt);
		ArgumentChecks.isTrue(percentage>=0);
		this.course=course;
		this.vehicleType=vt;
		this.percentage=percentage;
		Associations.Dedicate.link(course, this, vt);
	}

	public int getPercentage() {
		return percentage;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public Course getCourse() {
		return course;
	}
	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	void _setCourse(Course course) {
		this.course = course;
	}
	


}
