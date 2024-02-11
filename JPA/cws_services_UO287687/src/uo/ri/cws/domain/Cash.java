package uo.ri.cws.domain;

import uo.ri.util.assertion.ArgumentChecks;

public class Cash extends PaymentMean {

	Cash(){}
	public Cash(Client client) {
		ArgumentChecks.isNotNull(client);
		Associations.Pay.link(client, this);
	}

}
