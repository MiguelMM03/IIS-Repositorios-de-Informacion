package uo.ri.cws.application.business.certificate.assembler;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.vehicletype.assembler.VehicleTypeAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.certificate.CertificateGateway.CertificateDALDto;

public class CertificateAssembler {
	public static CertificateBLDto DALDtoCertificateDto(CertificateDALDto m){
		CertificateBLDto dto = new CertificateBLDto();
		dto.id = m.id;
		dto.version = m.version;

		dto.mechanic =  MechanicAssembler.toBLDto
				(PersistenceFactory.forMechanic().findById(m.mechanicId)).get();
		dto.vehicleType = VehicleTypeAssembler.vehicleTypeDALDtoToBLDto
				(PersistenceFactory.forVehicleType().findById(m.vehicleTypeId).get());
		dto.obtainedAt = m.date;
		return dto;
	}
	public static CertificateDALDto BLDtoCertificateDALDto(CertificateBLDto m){
		CertificateDALDto result = new CertificateDALDto();
		result.id = m.id;
		result.version = m.version;

		result.mechanicId=m.mechanic.id;
		result.vehicleTypeId=m.vehicleType.id;
		result.date=m.obtainedAt;
		return result;
	}
	public static List<CertificateBLDto> toCertificateDtoList(List<CertificateDALDto> listDALD){
		List<CertificateBLDto> list=new ArrayList<CertificateBLDto>();
		for(CertificateDALDto c: listDALD) {
			list.add(DALDtoCertificateDto(c));
		}
		return list;
	}
}
