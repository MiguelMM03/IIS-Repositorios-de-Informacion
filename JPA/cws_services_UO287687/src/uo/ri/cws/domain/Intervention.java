package uo.ri.cws.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

public class Intervention extends BaseEntity {
	// natural attributes
	private LocalDateTime date;
	private int minutes;

	// accidental attributes

	private WorkOrder workOrder;
	private Mechanic mechanic;
	private Set<Substitution> substitutions = new HashSet<>();

	Intervention() {
	}

	public Intervention(Mechanic mechanic, WorkOrder workorder, int minutes) {
		this(mechanic, workorder, LocalDateTime.now(), minutes);
	}

	public Intervention(Mechanic mechanic, WorkOrder workOrder,
			LocalDateTime date, int minutes) {
		ArgumentChecks.isNotNull(mechanic);
		ArgumentChecks.isNotNull(workOrder);
		ArgumentChecks.isNotNull(date);
		ArgumentChecks.isTrue(minutes >= 0);
		this.date = date.truncatedTo(ChronoUnit.MILLIS);
		this.minutes = minutes;
		this.workOrder = workOrder;
		this.mechanic = mechanic;
		Associations.Intervene.link(workOrder, this, mechanic);
	}

	void _setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public Set<Substitution> getSubstitutions() {
		return new HashSet<>(substitutions);
	}

	Set<Substitution> _getSubstitutions() {
		return substitutions;
	}

	WorkOrder _getWorkOrder() {
		return workOrder;
	}

	Mechanic _getMechanic() {
		return mechanic;
	}

	public double getAmount() {
		double money = workOrder.getVehicle()._getVehicleType()
				.getPricePerHour() / 60.0 * minutes;
		for (Substitution s : substitutions) {
			money += s.getAmount();
		}
		return money;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public int getMinutes() {
		return minutes;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}
}
