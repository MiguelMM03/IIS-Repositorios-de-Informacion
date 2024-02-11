package uo.ri.cws.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;

public class WorkOrder extends BaseEntity {
	public enum WorkOrderState {
		OPEN, ASSIGNED, FINISHED, INVOICED
	}

	// natural attributes
	private LocalDateTime date;
	private String description;
	private double amount = 0;
	private WorkOrderState state = WorkOrderState.OPEN;

	// accidental attributes
	private Vehicle vehicle;
	private Mechanic mechanic;
	private Invoice invoice;
	private Set<Intervention> interventions = new HashSet<>();

	WorkOrder() {
	}

	public WorkOrder(Vehicle vehicle) {
		this(LocalDateTime.now(), "no-description", 0, WorkOrderState.OPEN,
				vehicle);
	}

	public WorkOrder(Vehicle vehicle, String description) {
		this(LocalDateTime.now(), description, 0, WorkOrderState.OPEN, vehicle);
	}

	public WorkOrder(Vehicle vehicle, LocalDateTime date, String description) {
		this(date, description, 0, WorkOrderState.OPEN, vehicle);
	}

	public WorkOrder(LocalDateTime date, String description, double amount,
			WorkOrderState state, Vehicle vehicle) {
		ArgumentChecks.isNotBlank(description);
		ArgumentChecks.isNotNull(vehicle);
		ArgumentChecks.isNotNull(date);
		// ArgumentChecks.isTrue(amount.getAmount()>0);
		this.date = date.truncatedTo(ChronoUnit.MILLIS);
		this.description = description;
		this.amount = amount;
		this.state = state;
		this.vehicle = vehicle;
		Associations.Fix.link(vehicle, this);
	}

	/**
	 * Changes it to INVOICED state given the right conditions This method is
	 * called from Invoice.addWorkOrder(...)
	 * 
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not FINISHED, or -
	 *                               The work order is not linked with the
	 *                               invoice
	 */
	public void markAsInvoiced() {
		StateChecks.isTrue(isFinished());
		StateChecks.isNotNull(invoice);
		state = WorkOrderState.INVOICED;
	}

	/**
	 * Changes it to FINISHED state given the right conditions and computes the
	 * amount
	 *
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in ASSIGNED
	 *                               state, or - The work order is not linked
	 *                               with a mechanic
	 */
	public void markAsFinished() {
		StateChecks.isTrue(isAssigned(), "Work order is not assigned");
		StateChecks.isNotNull(mechanic);
		// Associations.Assign.unlink(mechanic, this);
		state = WorkOrderState.FINISHED;
		computeAmount();
	}

	public void markAsFinishFromInvoiced() {
		StateChecks.isTrue(isInvoiced(), "Work order not invoiced");
		state = WorkOrderState.FINISHED;
	}

	private void computeAmount() {
		double amount = 0;
		for (Intervention i : interventions) {
			amount += i.getAmount();
		}
		this.amount = amount;
	}

	/**
	 * Changes it back to FINISHED state given the right conditions This method
	 * is called from Invoice.removeWorkOrder(...)
	 * 
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not INVOICED, or -
	 *                               The work order is still linked with the
	 *                               invoice
	 */
	public void markBackToFinished() {
		StateChecks.isTrue(isInvoiced());
		StateChecks.isTrue(invoice == null);
		state = WorkOrderState.FINISHED;
	}

	/**
	 * Links (assigns) the work order to a mechanic and then changes its state
	 * to ASSIGNED
	 * 
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in OPEN state,
	 *                               or - The work order is already linked with
	 *                               another mechanic
	 */
	public void assignTo(Mechanic mechanic) {
		StateChecks.isTrue(isOpen());
		StateChecks.isNull(this.mechanic);
		Associations.Assign.link(mechanic, this);
		state = WorkOrderState.ASSIGNED;
	}

	/**
	 * Unlinks (deassigns) the work order and the mechanic and then changes its
	 * state back to OPEN
	 * 
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in ASSIGNED
	 *                               state
	 */
	public void desassign() {
		StateChecks.isTrue(isAssigned());
		Associations.Assign.unlink(mechanic, this);
		state = WorkOrderState.OPEN;
	}

	/**
	 * In order to assign a work order to another mechanic is first have to be
	 * moved back to OPEN state and unlinked from the previous mechanic.
	 * 
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in FINISHED
	 *                               state
	 */
	public void reopen() {
		StateChecks.isTrue(isFinished());
		state = WorkOrderState.OPEN;
		Associations.Assign.unlink(mechanic, this);
	}

	public Set<Intervention> getInterventions() {
		return new HashSet<>(interventions);
	}

	Set<Intervention> _getInterventions() {
		return interventions;
	}

	void _setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public double getAmount() {
		return this.amount;
	}

	public WorkOrderState getState() {
		return state;
	}

	Vehicle _getVehicle() {
		return vehicle;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public boolean isInvoiced() {
		return getState().equals(WorkOrderState.INVOICED);
	}

	public boolean isFinished() {
		return getState().equals(WorkOrderState.FINISHED);
	}

	public boolean isAssigned() {
		return getState().equals(WorkOrderState.ASSIGNED);
	}

	public boolean isOpen() {
		return getState().equals(WorkOrderState.OPEN);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
