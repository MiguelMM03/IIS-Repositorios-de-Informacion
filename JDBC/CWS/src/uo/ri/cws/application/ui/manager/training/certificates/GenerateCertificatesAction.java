package uo.ri.cws.application.ui.manager.training.certificates;

import uo.ri.cws.application.business.BusinessFactory;
import util.console.Console;
import util.menu.Action;

public class GenerateCertificatesAction implements Action {

    @Override
    public void execute() throws Exception {

	Console.println("Generating certificates...");

	int qty = BusinessFactory.forCertificateService().generateCertificates();

	Console.println(qty + " certificates generated");
    }

}
