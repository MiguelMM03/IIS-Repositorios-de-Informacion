package uo.ri.cws.domain;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

public class Substitution extends BaseEntity {
	// natural attributes
	private int quantity;

	// accidental attributes
	private SparePart sparePart;
	private Intervention intervention;

	Substitution() {
	}

	public Substitution(SparePart sparePart, Intervention intervention,
			int quantity) {
		ArgumentChecks.isNotNull(sparePart);
		ArgumentChecks.isNotNull(intervention);
		ArgumentChecks.isTrue(quantity > 0);
		this.sparePart = sparePart;
		this.intervention = intervention;
		this.quantity = quantity;
		Associations.Substitute.link(sparePart, this, intervention);
	}

	void _setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}

	void _setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	SparePart _getSparePart() {
		return sparePart;
	}

	public int getQuantity() {
		return quantity;
	}

	public SparePart getSparePart() {
		return sparePart;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	Intervention _getIntervention() {
		return intervention;
	}

	public double getAmount() {
		return quantity * sparePart.getPrice();
	}

}
