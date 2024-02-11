package uo.ri.cws.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;
import uo.ri.util.math.Round;

public class Invoice extends BaseEntity {
	public enum InvoiceState {
		NOT_YET_PAID, PAID
	}

	// natural attributes
	private Long number;
	private LocalDate date;
	private double amount = 0;
	private double vat;
	@Enumerated(EnumType.STRING)
	private InvoiceState state = InvoiceState.NOT_YET_PAID;

	// accidental attributes
	private Set<WorkOrder> workOrders = new HashSet<>();
	private Set<Charge> charges = new HashSet<>();

	Invoice() {
	}

	public Invoice(Long number) {
		this(number, LocalDate.now(), new ArrayList<WorkOrder>());
	}

	public Invoice(Long number, LocalDate date) {
		this(number, date, new ArrayList<WorkOrder>());
	}

	public Invoice(Long number, List<WorkOrder> workOrders) {
		this(number, LocalDate.now(), workOrders);
	}

	// full constructor
	public Invoice(Long number, LocalDate date, List<WorkOrder> workOrders) {
		ArgumentChecks.isTrue(number >= 0,
				"El número tiene que ser mayor o igual que cero");
		ArgumentChecks.isNotNull(date, "La fecha no puede ser nula");
		ArgumentChecks.isNotNull(workOrders,
				"La lista de workorders no puede ser null");
		this.number = number;
		this.date = date;
		workOrders.stream().forEach(w -> addWorkOrder(w));
	}

	/**
	 * Computes amount and vat (vat depends on the date)
	 */
	private void computeAmount() {
		double amountComputed = workOrders.stream()
				.mapToDouble(w -> w.getAmount()).sum();
		this.vat = date.isBefore(LocalDate.of(2012, 7, 1))
				? amountComputed * 0.18
				: amountComputed * 0.21;
		this.amount = Round.twoCents(amountComputed + this.vat);
	}

	/**
	 * Adds (double links) the workOrder to the invoice and updates the amount
	 * and vat
	 * 
	 * @param workOrder
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 */
	public void addWorkOrder(WorkOrder workOrder) {
		StateChecks.isTrue(isNotSettled());
		Associations.ToInvoice.link(this, workOrder);
		workOrder.markAsInvoiced();
		computeAmount();
	}

	/**
	 * Removes a work order from the invoice and recomputes amount and vat
	 * 
	 * @param workOrder
	 * @see UML_State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 */
	public void removeWorkOrder(WorkOrder workOrder) {
		StateChecks.isTrue(isNotSettled());
		Associations.ToInvoice.unlink(this, workOrder);
		workOrder.markAsFinishFromInvoiced();
		computeAmount();
	}

	/**
	 * Marks the invoice as PAID, but
	 * 
	 * @throws IllegalStateException if - Is already settled - Or the amounts
	 *                               paid with charges to payment means do not
	 *                               cover the total of the invoice
	 */
	public void settle() {
		ArgumentChecks.isTrue(isNotSettled());
		ArgumentChecks.isTrue(getChargesAmount() >= getAmount());
		this.state = InvoiceState.PAID;
	}

	private double getChargesAmount() {
		return charges.stream().mapToDouble(c -> c.getAmount()).sum();
	}

	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<>(workOrders);
	}

	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}

	public Set<Charge> getCharges() {
		return new HashSet<>(charges);
	}

	Set<Charge> _getCharges() {
		return charges;
	}

	public Long getNumber() {
		return number;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getAmount() {
		computeAmount();
		return amount;
	}

	public double getVat() {
		return vat;
	}

	public InvoiceState getState() {
		return state;
	}

	@Override
	public String toString() {
		return "Invoice [number=" + number + ", date=" + date + ", amount="
				+ amount + ", vat=" + vat + ", state=" + state + "]";
	}

	/**
	 * 
	 * @return true si no está pagada
	 */
	public boolean isNotSettled() {
		return state.equals(InvoiceState.NOT_YET_PAID);
	}

}
