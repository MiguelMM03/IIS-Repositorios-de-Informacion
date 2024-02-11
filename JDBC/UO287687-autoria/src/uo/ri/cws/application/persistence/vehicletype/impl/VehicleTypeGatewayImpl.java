package uo.ri.cws.application.persistence.vehicletype.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway;
import uo.ri.cws.application.persistence.vehicletype.assembler.VehicleTypeAssembler;
import util.jdbc.Jdbc;

public class VehicleTypeGatewayImpl implements VehicleTypeGateway{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(VehicleTypeDALDto t) {
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
	public void update(VehicleTypeDALDto t) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<VehicleTypeDALDto> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLETYPES_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return VehicleTypeAssembler.toVehicleTypeDALDto(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			if (rs != null) try { rs.close(); } catch(SQLException e) { /* ignore */ }
			if (pst != null) try { pst.close(); } catch(SQLException e) { /* ignore */ }

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VehicleTypeDALDto> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLETYPES_FINDALL"));
			
			rs = pst.executeQuery();
			return VehicleTypeAssembler.toVehicleTypeDALDtoList(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			if (rs != null) try { rs.close(); } catch(SQLException e) { /* ignore */ }
			if (pst != null) try { pst.close(); } catch(SQLException e) { /* ignore */ }

		}
	}

}
