package uo.ri.cws.application.ui.manager.training.reports.actions;

import java.util.List;

import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class ListCertificationsByVehicleTypeAction implements Action {

	@Override
	public void execute() throws Exception {

		List<CertificateBLDto> rows = BusinessFactory.forCertificateService().findCertificatesByVehicleTypeId();

		Console.println("Certificates by vehicle type");
		rows.forEach(r -> Printer.printCertificateRow(r));
	}
}