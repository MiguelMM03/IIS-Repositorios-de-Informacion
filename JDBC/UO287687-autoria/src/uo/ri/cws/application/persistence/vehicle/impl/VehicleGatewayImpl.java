package uo.ri.cws.application.persistence.vehicle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.assembler.VehicleAssembler;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;
import util.jdbc.Jdbc;

public class VehicleGatewayImpl implements VehicleGateway{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(VehicleDALDto t) {
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
	public void update(VehicleDALDto t) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<VehicleDALDto> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return VehicleAssembler.toVehicleDALDto(rs);
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
	public List<VehicleDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<VehicleDALDto> findByPlate(String plate) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FINDBYPLATE"));
			pst.setString(1, plate);
			rs = pst.executeQuery();
			return VehicleAssembler.toVehicleDALDto(rs);
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
	public List<VehicleDALDto> findByMake(String make) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VehicleDALDto> findByClient(String idCliente) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FINDBYCLIENT"));
			pst.setString(1, idCliente);
			
			rs = pst.executeQuery();
			
			List<VehicleDALDto> vehicles = WorkOrderAssembler.toVehicleDALDtoList(rs);
			return vehicles;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			if (rs != null) try { rs.close(); } catch(SQLException e) { /* ignore */ }
			if (pst != null) try { pst.close(); } catch(SQLException e) { /* ignore */ }

		}

	}

}
