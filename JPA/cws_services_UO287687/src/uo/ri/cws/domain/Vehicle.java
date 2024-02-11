package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

public class Vehicle extends BaseEntity {
	private String plateNumber;
	private String make;
	private String model;
	
	private Client client;
	private Set<WorkOrder> workOrders=new HashSet<>();
	private VehicleType vehicleType;
	
	Vehicle(){}
	public Vehicle(String plateNumber) {
		this(plateNumber, "no-make","no-model");
	}
	public Vehicle(String plateNumber, String make, String model) {
		ArgumentChecks.isNotBlank(plateNumber);
		ArgumentChecks.isNotBlank(make);
		ArgumentChecks.isNotBlank(model);
		this.plateNumber = plateNumber;
		this.make = make;
		this.model = model;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}

	@Override
	public String toString() {
		return "Vehicle [plateNumber=" + plateNumber + ", make=" + make
				+ ", model=" + model + "]";
	}
	 Client _getCliente() {
		return client;
	}
	void _setCliente(Client cliente) {
		this.client = cliente;
	}
	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workOrders);
	}
	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}
	VehicleType _getVehicleType() {
		return vehicleType;
	}
	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Client getClient() {
		return client;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
}
