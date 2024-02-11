package uo.ri.cws.application.persistence.workorder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;
import util.jdbc.Jdbc;

public class WorkOrderGatewayImpl implements WorkOrderGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(WorkOrderDALDto t) {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_ADD"));
			pst.setString(1, t.id);
			pst.setDouble(2, t.amount);
			pst.setTimestamp(3, Timestamp.valueOf(t.date));
			pst.setString(4, t.description);
			pst.setString(5, t.status);
			pst.setString(6, t.invoiceId);
			pst.setString(7, t.mechanicId);
			pst.setString(8, t.vehicleId);
			pst.setLong(9, 1L);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
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
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_REMOVE"));
			pst.setString(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
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
	public void update(WorkOrderDALDto t) {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_UPDATE"));

			pst.setDouble(1, t.amount);
			pst.setTimestamp(2, Timestamp.valueOf(t.date));
			pst.setString(3, t.description);
			pst.setString(4, t.status);
			pst.setString(5, t.invoiceId);
			pst.setString(6, t.mechanicId);
			pst.setString(7, t.vehicleId);
			;
			pst.setString(8, t.id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
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
	public Optional<WorkOrderDALDto> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDto(rs);
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
	public List<WorkOrderDALDto> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDALL"));
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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
	public List<WorkOrderDALDto> findByMechanic(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYMECHANIC"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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
	public List<WorkOrderDALDto> findNotInvoicedForVehicles(List<String> vehicleIds) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			List<WorkOrderDALDto> list = new ArrayList<WorkOrderGateway.WorkOrderDALDto>();
			for (String id : vehicleIds) {
				pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDNOTINVOICEDBYVEHICLE"));
				pst.setString(1, id);
				rs = pst.executeQuery();
				list.addAll(WorkOrderAssembler.toWorkOrderDALDtoList(rs));
			}
			return list;
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
	public List<WorkOrderDALDto> findByVehicleId(String vehicleId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYVEHICLE"));
			pst.setString(1, vehicleId);
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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
	public List<WorkOrderDALDto> findByIds(List<String> arg) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			List<WorkOrderDALDto> wo = new ArrayList<WorkOrderGateway.WorkOrderDALDto>();
			for (String s : arg) {
				pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYID"));
				pst.setString(1, s);
				rs = pst.executeQuery();
				wo.addAll(WorkOrderAssembler.toWorkOrderDALDtoList(rs));
			}
			return wo;
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
	public List<WorkOrderDALDto> findByInvoice(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYINVOICE"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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
	public List<WorkOrderDALDto> findInvoiced() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDINVOICED"));
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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
	public List<WorkOrderDALDto> findInvoicedByMechanic(String mechanicId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDINVOICEDBYMECHANIC"));
			pst.setString(1, mechanicId);
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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
	public List<WorkOrderDALDto> findUnfinished() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDUNFINISHED"));
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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
