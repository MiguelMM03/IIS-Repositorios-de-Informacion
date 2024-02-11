package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;


public class VehicleType extends BaseEntity{
	// natural attributes
	
	private String name;
	private double pricePerHour;
	
	private int minTrainingHours;

	// accidental attributes
	private Set<Vehicle> vehicles = new HashSet<>();
	private Set<Certificate> certificates=new HashSet<Certificate>();
	private Set<Dedication> dedications=new HashSet<Dedication>();

	public Set<Vehicle> getVehicles() {
		return new HashSet<>( vehicles );
	}

	Set<Vehicle> _getVehicles() {
		return vehicles;
	}
	
	VehicleType() {}
	
	public VehicleType(String name) {
		this(name,0.0);
	}

	public VehicleType(String name, double pricePerHour) {
		ArgumentChecks.isNotBlank(name);
		this.name = name;
		this.pricePerHour = pricePerHour;
	}

	public String getName() {
		return name;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public int getMinTrainingHours() {
		return minTrainingHours;
	}



	@Override
	public String toString() {
		return "VehicleType [name=" + name + ", pricePerHour=" + pricePerHour
				+ ", minTrainingHours=" + minTrainingHours + "]";
	}

	public Set<Certificate> getCertificates() {
		return new HashSet<Certificate>(certificates);
	}

	public Set<Dedication> getDedications() {
		return new HashSet<Dedication>(dedications);
	}
	Set<Dedication> _getDedications() {
		return dedications;
	}
	Set<Certificate> _getCertificates() {
		return certificates;
	}
	
	

	
	

}
