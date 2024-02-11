package uo.ri.cws.application.service.certificate.impl;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.certificate.CertificateService;
import uo.ri.cws.application.service.certificate.impl.commands.FindCertificatesByVehicleTypeId;
import uo.ri.cws.application.service.certificate.impl.commands.GenerateCertificates;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CertificateServiceImpl implements CertificateService{

	private CommandExecutor executor = Factory.executor.forExecutor();
	
	@Override
	public int generateCertificates() throws BusinessException {
		// TODO Auto-generated method stub
		return executor.execute(new GenerateCertificates());
	}

	@Override
	public List<CertificateDto> findCertificatesByVehicleTypeId(String id)
			throws BusinessException {
		return executor.execute(new FindCertificatesByVehicleTypeId(id));
	}

	@Override
	public List<CertificateDto> findCertificatesSortedByVehicleType()
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
