package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import uo.ri.cws.domain.base.BaseEntity;

public class Mechanic extends BaseEntity{
	// natural attributes
	private String dni;
	private String surname;
	private String name;

	// accidental attributes
	private Set<WorkOrder> assigned = new HashSet<>();
	private Set<Intervention> interventions = new HashSet<>();
	private Set<Certificate> certificates=new HashSet<Certificate>();
	private Set<Enrollment> enrollments=new HashSet<Enrollment>();

	Mechanic(){}
	public Mechanic(String dni) {
		this(dni,"unknown", "unknown");
	}
	public Mechanic(String dni, String surname, String name) {
		super();
		this.dni = dni;
		this.surname = surname;
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<WorkOrder> getAssigned() {
		return new HashSet<>( assigned );
	}

	Set<WorkOrder> _getAssigned() {
		return assigned;
	}

	public Set<Intervention> getInterventions() {
		return new HashSet<>( interventions );
	}

	Set<Intervention> _getInterventions() {
		return interventions;
	}

	public String getDni() {
		return dni;
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}
	Set<Certificate> _getCertificates() {
		return certificates;
	}
	public Set<Certificate> getCertificates() {
		return new HashSet<>(certificates);
	}
	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}
	public Set<Enrollment> getEnrollments() {
		return new HashSet<>(enrollments);
	}

	public boolean isCertifiedFor(VehicleType vt) {
		for(Certificate c:certificates) {
			if(c.getVehicleType().equals(vt)) {
				return true;
			}
		}
		return false;
	}
	public Set<Enrollment> getEnrollmentsFor(VehicleType vt){
		Set<Enrollment> result=new HashSet<Enrollment>();
		for(Enrollment e: enrollments) {
			for(Dedication dedication:e.getCourse().getDedications()) {
				if(dedication.getVehicleType().equals(vt)) {
					result.add(e);
				}
			}
		}
		return result;
	}
	
	

}
