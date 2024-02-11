package uo.ri.cws.application.service.certificate.impl.commands;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.certificate.CertificateService.CertificateDto;
import uo.ri.cws.application.service.certificate.impl.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.util.assertion.ArgumentChecks;

public class FindCertificatesByVehicleTypeId
		implements Command<List<CertificateDto>> {

	private String id;

	public FindCertificatesByVehicleTypeId(String id) {
		ArgumentChecks.isNotBlank(id, "Vehicle type id can't be empty");
		this.id = id;
	}

	@Override
	public List<CertificateDto> execute() throws BusinessException {
		return DtoAssembler.toCertificateDtoList(
				Factory.repository.forCertificate().findByVehicleTypeId(id));
	}
}
