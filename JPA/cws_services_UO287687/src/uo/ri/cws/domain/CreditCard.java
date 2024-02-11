package uo.ri.cws.domain;

import java.time.LocalDate;

import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;


public class CreditCard extends PaymentMean {
	private String number;
	private String type;
	private LocalDate validThru;
	CreditCard(){}
	public CreditCard(String number) {
		this(0.0,number,"UNKNOWN",LocalDate.now().plusDays(1));
	}
	public CreditCard(String number,String type,LocalDate validThru) {
		this(0.0,number,type,validThru);
	}
	public CreditCard(double accumulated, String number, String type,
			LocalDate validThru) {
		ArgumentChecks.isNotBlank(number);
		ArgumentChecks.isNotBlank(type);
		ArgumentChecks.isNotNull(validThru);
		//StateChecks.isTrue(validThru.isAfter(LocalDate.now()), 
		//		"Credit card has expired");
		this.number = number;
		this.type = type;
		this.validThru = validThru;
	}
	public String getNumber() {
		return number;
	}
	public String getType() {
		return type;
	}
	public LocalDate getValidThru() {
		return validThru;
	}
	public boolean isValidNow() {
		return LocalDate.now().isBefore(validThru);
	}
	public void setValidThru(LocalDate date) {
		ArgumentChecks.isNotNull(date);
		this.validThru=date;
	}

	@Override
	public String toString() {
		return "CreditCard [number=" + number + ", type=" + type
				+ ", validThru=" + validThru + "]";
	}
	@Override
	public void pay(double importe) {
		StateChecks.isTrue(isValidNow());
		super.pay(importe);
	}

}
