package uo.ri.cws.application.persistence.dedication.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.dedication.DedicationGateway;
import uo.ri.cws.application.persistence.util.Conf;
import util.jdbc.Jdbc;

public class DedicationGatewayImpl implements DedicationGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(DedicationDALDto t) {
		// TODO Auto-generated method stub

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
	public void update(DedicationDALDto t) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<DedicationDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DedicationDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int findPercentageForVehicleTypeInCourse(String vehicleTypeId,
			String course_id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			int value = 0;
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty(
					"TDEDICATIONS_FINDPERCENTAGEFORVEHICLETYPEINCOURSE"));
			pst.setString(1, vehicleTypeId);
			pst.setString(2, course_id);
			rs = pst.executeQuery();
			if (rs.next())
				value = rs.getInt(1);
			return value;
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
