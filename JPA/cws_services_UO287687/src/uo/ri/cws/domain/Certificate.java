package uo.ri.cws.domain;

import java.time.LocalDate;

import uo.ri.cws.domain.base.BaseEntity;

public class Certificate extends BaseEntity {

	private Mechanic mechanic;
	private VehicleType vehicleType;
	private LocalDate date;

	Certificate() {
	}

	public Certificate(Mechanic mechanic, VehicleType vehicleType) {
		this(mechanic, vehicleType, LocalDate.now());
	}

	public Certificate(Mechanic mechanic, VehicleType vehicleType,
			LocalDate date) {
		super();
		this.date = date;
		Associations.Certify.link(mechanic, this, vehicleType);
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public LocalDate getDate() {
		return date;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

}
