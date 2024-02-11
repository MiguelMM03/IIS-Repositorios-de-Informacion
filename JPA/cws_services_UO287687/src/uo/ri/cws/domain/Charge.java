package uo.ri.cws.domain;

import uo.ri.cws.domain.Invoice.InvoiceState;
import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.StateChecks;

public class Charge extends BaseEntity {
	// natural attributes
	private double amount = 0.0;

	// accidental attributes
	private Invoice invoice;
	private PaymentMean paymentMean;

	Charge() {
	}

	public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
		this.amount = amount;
		paymentMean.pay(amount);
		Associations.ToCharge.link(paymentMean, this, invoice);
	}

	/**
	 * Unlinks this charge and restores the accumulated to the payment mean
	 * 
	 * @throws IllegalStateException if the invoice is already settled
	 */
	public void rewind() {
		StateChecks
				.isTrue(invoice.getState().equals(InvoiceState.NOT_YET_PAID));
		paymentMean.pay(-amount);
		Associations.ToCharge.unlink(this);
	}

	public double getAmount() {
		return amount;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public PaymentMean getPaymentMean() {
		return paymentMean;
	}

	Invoice _getInvoice() {
		return invoice;
	}

	PaymentMean _getPaymentMean() {
		return paymentMean;
	}

	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	void _setPaymentMean(PaymentMean paymentMean) {
		this.paymentMean = paymentMean;
	}

	@Override
	public String toString() {
		return "Charge [amount=" + amount + "]";
	}

}
