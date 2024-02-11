package uo.ri.cws.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;

public class Course extends BaseEntity {
	private String code;
	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int hours;

	private Set<Enrollment> enrollments = new HashSet<Enrollment>();
	private Set<Dedication> dedications = new HashSet<Dedication>();

	Course() {
	}

	public Course(String code) {
		this(code, "no-name", "no-description", LocalDate.now(),
				LocalDate.now(), 0);
	}

	public Course(String code, String name, String description,
			LocalDate startDate, LocalDate endDate, int hours) {
		super();
		ArgumentChecks.isNotBlank(code);
		ArgumentChecks.isNotBlank(name);
		ArgumentChecks.isNotBlank(description);
		ArgumentChecks.isNotNull(startDate);
		ArgumentChecks.isNotNull(endDate);
		ArgumentChecks.isTrue(hours >= 0);
		ArgumentChecks.isFalse(endDate.isBefore(startDate));
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hours = hours;
	}

	public Course(String code, String name, String description,
			LocalDate startDate, LocalDate endDate, int hours,
			Set<Enrollment> enrollments, Set<Dedication> dedications) {
		this(code, name, description, startDate, endDate, hours);
		this.enrollments = enrollments;
		this.dedications = dedications;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public int getHours() {
		return hours;
	}

	public Set<Enrollment> getEnrollments() {
		return new HashSet<>(enrollments);
	}

	public Set<Dedication> getDedications() {
		return new HashSet<>(dedications);
	}

	public void addDedications(Map<VehicleType, Integer> percentages) {
		ArgumentChecks.isNotNull(percentages);
		StateChecks.isTrue(dedications.isEmpty(), "Course has percentages");
		int sum = 0;
		for (Integer percentage : percentages.values()) {
			sum += percentage;
		}
		ArgumentChecks.isTrue(sum == 100, "Percentage must sum 100%");

		for (VehicleType vt : percentages.keySet()) {
			int percentage = percentages.get(vt);
			dedications.add(new Dedication(this, vt, percentage));
		}
	}

	public void clearDedications() {
		for (Dedication d : getDedications()) {
			Associations.Dedicate.unlink(d);
		}
	}

	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}

	Set<Dedication> _getDedications() {
		return dedications;
	}

}
