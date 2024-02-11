package uo.ri.cws.application.business.certificate.crud.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.business.certificate.assembler.CertificateAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.certificate.CertificateGateway.CertificateDALDto;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;
import util.assertion.Argument;

public class FindCertificatesBeforeYearByMechanicDni implements Command<List<CertificateBLDto>>{

	private LocalDate date;
	private String dni;
	public FindCertificatesBeforeYearByMechanicDni(String dni,int year, int month, int day) {
		Argument.isNotEmpty(dni, "El dni no puede estar vacío");
		Argument.isTrue(year>=0, "El año debe ser positivo");
		Argument.isTrue(month>0 && month<=12,"Mes inválido");
		Argument.isTrue(day<31 && day>0, "Dia inválido");
		this.date=LocalDate.of(year, month, day);
		this.dni=dni;
	}
	@Override
	public List<CertificateBLDto> execute() throws BusinessException {
		Optional<MechanicDALDto> mechanic=PersistenceFactory.forMechanic().findByDni(dni);
		if(mechanic.isEmpty()) {
			throw new BusinessException("Mechanic doesn't exist");
		}
		List<CertificateDALDto> certificates=PersistenceFactory.forCertificate().
				findCertificatesBeforeYearByMechanic(date,mechanic.get().id);
		return CertificateAssembler.toCertificateDtoList(certificates);
	}

}
