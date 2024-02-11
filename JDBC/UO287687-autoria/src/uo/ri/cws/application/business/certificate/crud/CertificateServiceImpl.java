package uo.ri.cws.application.business.certificate.crud;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.certificate.CertificateService;
import uo.ri.cws.application.business.certificate.crud.commands.FindCertificatesBeforeYearByMechanicDni;
import uo.ri.cws.application.business.certificate.crud.commands.FindCertificatesByVehicleType;
import uo.ri.cws.application.business.certificate.crud.commands.FindCertificatesByVehicleTypeId;
import uo.ri.cws.application.business.certificate.crud.commands.GenerateCertificates;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class CertificateServiceImpl implements CertificateService {

	CommandExecutor c = new CommandExecutor();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int generateCertificates() throws BusinessException {
		return c.execute(new GenerateCertificates());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CertificateBLDto> findCertificatesByVehicleTypeId(
			String vehicleTypeId) throws BusinessException {
		return c.execute(new FindCertificatesByVehicleTypeId(vehicleTypeId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CertificateBLDto> findCertificatesByVehicleTypeId()
			throws BusinessException {
		return c.execute(new FindCertificatesByVehicleType());
	}

	@Override
	public List<CertificateBLDto> findCertificatesBeforeYearByMechanicDni(
			String dni, int year, int month, int day) throws BusinessException {
		return c.execute(new FindCertificatesBeforeYearByMechanicDni(dni,year, month, day));
	}

}
