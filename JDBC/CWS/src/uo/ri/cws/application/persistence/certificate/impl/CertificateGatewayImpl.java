package uo.ri.cws.application.persistence.certificate.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.certificate.CertificateGateway;
import uo.ri.cws.application.persistence.certificate.assembler.CertificateAssembler;
import uo.ri.cws.application.persistence.util.Conf;
import util.jdbc.Jdbc;

public class CertificateGatewayImpl implements CertificateGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(CertificateDALDto t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TCERTIFICATES_ADD"));
			pst.setString(1, t.id);
			pst.setDate(2, Date.valueOf(t.date));
			pst.setLong(3, 1L);
			pst.setString(4, t.mechanicId);
			pst.setString(5, t.vehicleTypeId);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignore */ }
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(CertificateDALDto t) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<CertificateDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<CertificateDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CertificateDALDto> findCertificatesByVehicleTypeId(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TCERTIFICATES_FINDBYVEHICLETYPEID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return CertificateAssembler.toCertificateDALDtoList(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignore */ }
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<CertificateDALDto> findCertificatesByVehicleTypeIdAndMechanic(
			String vehicleTypeId, String mechanicId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty(
					"TCERTIFICATES_FINDBYVEHICLETYPEIDANDMECHANIC"));
			pst.setString(1, vehicleTypeId);
			pst.setString(2, mechanicId);
			rs = pst.executeQuery();
			return CertificateAssembler.toOptionalCertificateDALDto(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignore */ }
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }

		}
	}

}
