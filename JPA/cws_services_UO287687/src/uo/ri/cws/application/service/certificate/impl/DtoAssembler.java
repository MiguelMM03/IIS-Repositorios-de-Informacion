package uo.ri.cws.application.service.certificate.impl;

import java.util.List;
import java.util.stream.Collectors;

import uo.ri.cws.application.service.certificate.CertificateService.CertificateDto;
import uo.ri.cws.application.service.certificate.CertificateService.MechanicForCertificationDto;
import uo.ri.cws.application.service.certificate.CertificateService.VehicleTypeForCertificationDto;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class DtoAssembler {

	public static MechanicForCertificationDto toDto(Mechanic m) {
		MechanicForCertificationDto dto = new MechanicForCertificationDto();
		dto.id = m.getId();
		dto.version = m.getVersion();

		dto.dni = m.getDni();
		dto.name = m.getName();
		dto.surname = m.getSurname();
		return dto;
	}

	public static List<CertificateDto> toCertificateDtoList(
			List<Certificate> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

	public static CertificateDto toDto(Certificate c) {
		CertificateDto dto = new CertificateDto();
		dto.id = c.getId();
		dto.version = c.getVersion();

		dto.mechanic = toDto( c.getMechanic() );
		dto.vehicleType = toDto( c.getVehicleType() );
		dto.obtainedAt = c.getDate();

		return dto;
	}

	public static VehicleTypeForCertificationDto toDto(VehicleType vt) {
		VehicleTypeForCertificationDto dto = new VehicleTypeForCertificationDto();

		dto.id = vt.getId();
		dto.version = vt.getVersion();

		dto.name = vt.getName();
		dto.pricePerHour = vt.getPricePerHour();
		dto.minTrainigHours = vt.getMinTrainingHours();

		return dto;
	}

}
