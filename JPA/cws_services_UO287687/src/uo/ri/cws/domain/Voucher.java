package uo.ri.cws.domain;

import uo.ri.util.assertion.StateChecks;

public class Voucher extends PaymentMean {
	private String code;
	private double available = 0.0;
	private String description;

	Voucher() {
	}

	public Voucher(String code, double available) {
		this(code, "no-description", available);
	}

	public Voucher(String code, String description, double available) {
		this.code = code;
		this.available = available;
		this.description = description;
	}

	/**
	 * Augments the accumulated (super.pay(amount) ) and decrements the
	 * available
	 * 
	 * @throws IllegalStateException if not enough available to pay
	 */
	@Override
	public void pay(double amount) {
		StateChecks.isTrue(amount <= available, "Not enought available to pay");
		super.pay(amount);
		this.available -= amount;
	}

	public String getCode() {
		return code;
	}

	public double getAvailable() {
		return available;
	}

	public String getDescription() {
		return description;
	}

}
