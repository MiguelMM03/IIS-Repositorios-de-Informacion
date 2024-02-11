package uo.ri.cws.application.persistence.invoice.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.assembler.InvoiceAssembler;
import uo.ri.cws.application.persistence.util.Conf;
import util.jdbc.Jdbc;

public class InvoiceGatewayImpl implements InvoiceGateway{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(InvoiceDALDto t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_ADD"));
			pst.setString(1, t.id);
			pst.setLong(2, t.number);
			pst.setDate(3, Date.valueOf(t.date));
			pst.setDouble(4, t.vat);
			pst.setDouble(5, t.amount);
			pst.setString(6, t.status);
			pst.setLong(7, 1L);
			
			pst.executeUpdate();
			
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
	public void remove(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_REMOVE"));
			pst.setString(1, id);
			
			pst.executeUpdate();
			
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
	public void update(InvoiceDALDto t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_UPDATE"));

			pst.setDate(1, Date.valueOf(t.date));
			pst.setDouble(2, t.vat);
			pst.setDouble(3, t.amount);
			pst.setString(4, t.status);
			pst.setLong(5, 1L);
			pst.setString(6, t.id);
			pst.setLong(7, t.number);
			
			pst.executeUpdate();
			
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
	public Optional<InvoiceDALDto> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return InvoiceAssembler.toInvoiceDALDto(rs);
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
	public List<InvoiceDALDto> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_FINDALL"));
			rs = pst.executeQuery();
			return InvoiceAssembler.toInvoiceList(rs);
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
	public Optional<InvoiceDALDto> findByNumber(Long number) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_FINDBYNUMBER"));
			pst.setLong(1, number);
			rs = pst.executeQuery();
			return InvoiceAssembler.toInvoiceDALDto(rs);
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
	public Long getNextInvoiceNumber() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_LASTNUMBER"));
			rs = pst.executeQuery();
			rs.next();
			return rs.getLong(1)+1;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			if (rs != null) try { rs.close(); } catch(SQLException e) { /* ignore */ }
			if (pst != null) try { pst.close(); } catch(SQLException e) { /* ignore */ }

		}
	}


}
