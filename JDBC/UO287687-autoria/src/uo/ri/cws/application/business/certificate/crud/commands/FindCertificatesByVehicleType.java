package uo.ri.cws.application.business.certificate.crud.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.business.certificate.assembler.CertificateAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.business.vehicletype.assembler.VehicleTypeAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.certificate.CertificateGateway;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway;

public class FindCertificatesByVehicleType implements Command<List<CertificateBLDto>> {

	private CertificateGateway cg = PersistenceFactory.forCertificate();
	private VehicleTypeGateway vtg = PersistenceFactory.forVehicleType();

	@Override
	public List<CertificateBLDto> execute() throws BusinessException {
		List<CertificateBLDto> certificates=new ArrayList<CertificateBLDto>();
		List<VehicleTypeBLDto> vehiclesTypes=VehicleTypeAssembler.toVehicleTypeDtoList(vtg.findAll());
		Collections.sort(vehiclesTypes, Comparator.comparing(vt -> vt.id));
		for(VehicleTypeBLDto vehicleType:vehiclesTypes) {
			List<CertificateBLDto> c=CertificateAssembler.toCertificateDtoList(
					cg.findCertificatesByVehicleTypeId(vehicleType.id));
			Collections.sort(c, Comparator.comparing(cert -> cert.mechanic.id));
			certificates.addAll(c);
		}
		return certificates;
	}
}
