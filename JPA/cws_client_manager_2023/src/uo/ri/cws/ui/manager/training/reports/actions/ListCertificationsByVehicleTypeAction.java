package uo.ri.cws.ui.manager.training.reports.actions;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.certificate.CertificateService;
import uo.ri.cws.application.service.certificate.CertificateService.CertificateDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class ListCertificationsByVehicleTypeAction implements Action {

	@Override
	public void execute() throws Exception {

		CertificateService rs = Factory.service.forCertificateService();
		List<CertificateDto> rows = rs.findCertificatesSortedByVehicleType();

		Console.println("Certificates by vehicle type");
		rows.forEach( r ->
			Printer.printCertificateRow( r )
		);
	}

}
