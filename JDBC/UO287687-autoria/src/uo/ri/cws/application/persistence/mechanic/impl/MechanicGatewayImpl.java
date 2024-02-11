package uo.ri.cws.application.persistence.mechanic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.persistence.util.Conf;
import util.jdbc.Jdbc;

public class MechanicGatewayImpl implements MechanicGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(MechanicDALDto t) {
		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_ADD"));
			pst.setString(1, t.id);
			pst.setString(2, t.dni);
			pst.setString(3, t.name);
			pst.setString(4, t.surname);
			pst.setLong(5, 1L);

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
			// No se cierra conexion tal y como está implementado para que sea un
			// TransactionScript
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

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_REMOVE"));
			pst.setString(1, id);

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
	public void update(MechanicDALDto t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_UPDATE"));
			pst.setString(1, t.name);
			pst.setString(2, t.surname);
			pst.setString(3, t.id);

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
	public Optional<MechanicDALDto> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return MechanicAssembler.toMechanicDALDto(rs);
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
	public List<MechanicDALDto> findByIds(List<String> ids) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			List<MechanicDALDto> mechanics = new ArrayList<MechanicGateway.MechanicDALDto>();
			c = Jdbc.getCurrentConnection();
			for (String id : ids) {
				pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDBYID"));
				pst.setString(1, id);
				rs = pst.executeQuery();
				Optional<MechanicDALDto> m = MechanicAssembler.toMechanicDALDto(rs);
				if (m.isPresent())
					mechanics.add(m.get());
			}
			return mechanics;
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
	public List<MechanicDALDto> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDALL"));

			rs = pst.executeQuery();
			return MechanicAssembler.toMechanicDALDtoList(rs);
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
	public Optional<MechanicDALDto> findByDni(String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDBYDNI"));
			pst.setString(1, dni);
			rs = pst.executeQuery();
			return MechanicAssembler.toMechanicDALDto(rs);

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
