package uo.ri.cws.ui.foreman.reception.actions;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.certificate.CertificateService;
import uo.ri.cws.application.service.certificate.CertificateService.CertificateDto;
import uo.ri.cws.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

public class ListCertifiedMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String vtId = Console.readString("Vehicle type id");

		CertificateService as = Factory.service.forCertificateService();
		List<CertificateDto> certs = as.findCertificatesByVehicleTypeId( vtId );

		Console.println("\nList of certified mechanics\n");
		for(CertificateDto m : certs) {
			Printer.printCertifiedMechanic( m );
		}

	}
}
