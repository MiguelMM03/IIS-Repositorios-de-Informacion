package uo.ri.cws.ui.manager.training.certificates;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.certificate.CertificateService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class GenerateCertificatesAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Console.println("Generating certificates...");
		
		CertificateService cs = Factory.service.forCertificateService();
		int qty = cs.generateCertificates();
		
		Console.println(qty + " certificates generated");
	}

}
