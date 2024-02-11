package uo.ri.cws.application.ui.foreman.reception.action;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.ui.util.Printer;
import util.console.Console;
import util.menu.Action;

public class ListCertifiedMechanicsAction implements Action {

    @Override
    public void execute() throws BusinessException {

	Printer.printVehicleTypes(
		BusinessFactory.forVehicleTypeService().findAllVehicleTypes());

	String vtId = Console.readString("Vehicle type id");
	List<CertificateBLDto> certs = BusinessFactory.forCertificateService().findCertificatesByVehicleTypeId(vtId);

	Console.println("\nList of certified mechanics\n");
	for (CertificateBLDto m : certs) {
	    Printer.printCertifiedMechanic(m);
	}

    }
}
