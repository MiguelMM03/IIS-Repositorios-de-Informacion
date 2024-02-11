package uo.ri.cws.application.ui.manager.training.certificates;

import java.util.List;

import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class FindCertificatesBeforeDateByMechanicAction implements Action {

	@Override
	public void execute() throws Exception {

		String dni = Console.readString("DNI");
		int year = Console.readInt("Year");
		int month = Console.readInt("Month");
		int day = Console.readInt("day");
		List<CertificateBLDto> certificates = BusinessFactory
				.forCertificateService()
				.findCertificatesBeforeYearByMechanicDni(dni, year, month, day);

		for (CertificateBLDto c : certificates) {
			Printer.printCertificateRow(c);
		}
	}
}
