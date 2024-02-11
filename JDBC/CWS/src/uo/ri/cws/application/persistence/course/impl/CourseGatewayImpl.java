package uo.ri.cws.application.persistence.course.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.course.CourseGateway;
import uo.ri.cws.application.persistence.course.assembler.CourseAssembler;
import uo.ri.cws.application.persistence.util.Conf;
import util.jdbc.Jdbc;

public class CourseGatewayImpl implements CourseGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(CourseDALDto t) {
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
	public void update(CourseDALDto t) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<CourseDALDto> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TCOURSES_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return CourseAssembler.toOptionalCourseDALDto(rs);
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
	public List<CourseDALDto> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TCOURSES_FINDALL"));
			rs = pst.executeQuery();
			return CourseAssembler.toCourseDALDtoList(rs);
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
	public List<CourseDALDto> findAllFinishedCourses() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TCOURSES_FINDALLFINISHED"));
			pst.setDate(1, Date.valueOf(LocalDate.now()));
			rs = pst.executeQuery();
			return CourseAssembler.toCourseDALDtoList(rs);
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
