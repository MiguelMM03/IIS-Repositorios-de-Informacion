package uo.ri.cws.application.persistence.enrollment.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.course.CourseGateway.CourseDALDto;
import uo.ri.cws.application.persistence.enrollment.EnrollmentGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;
import uo.ri.cws.application.persistence.util.Conf;
import util.jdbc.Jdbc;
/*Algunos métodos de esta clase tienen lógica de negocio */
public class EnrollmentGatewayImpl implements EnrollmentGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(EnrollmentDALDto t) {
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
	public void update(EnrollmentDALDto t) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<EnrollmentDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EnrollmentDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MechanicDALDto> findMechanicWithPassedCourse(String courseId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			List<MechanicDALDto> mechanics = new ArrayList<MechanicGateway.MechanicDALDto>();
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TENROLLMENTS_FINDMECHANICSWITHPASSEDCOURSE"));
			pst.setString(1, courseId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Optional<MechanicDALDto> m = PersistenceFactory.forMechanic()
						.findById(rs.getString("mechanic_id"));
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
	public List<CourseDALDto> findCoursesPassedByMechanic(String mechanicId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			List<CourseDALDto> courses = new ArrayList<CourseDALDto>();
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TENROLLMENTS_FINDCOURSESPASSEDBYMECHANIC"));
			pst.setString(1, mechanicId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Optional<CourseDALDto> m = PersistenceFactory.forCourse()
						.findById(rs.getString("course_id"));
				if (m.isPresent())
					courses.add(m.get());
			}
			return courses;
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
	public int findAttendanceByCourseAndMechanic(String courseId,
			String mechanicId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			int value = 0;
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty(
					"TENROLLMENTS_FINDATTENDANCEBYCOURSEANDMECHANIC"));
			pst.setString(1, courseId);
			pst.setString(2, mechanicId);
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CourseDALDto> findCoursesByMechanic(String mechanicId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			List<CourseDALDto> courses = new ArrayList<CourseDALDto>();
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TENROLLMENTS_FINDCOURSESBYMECHANIC"));
			pst.setString(1, mechanicId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Optional<CourseDALDto> m = PersistenceFactory.forCourse()
						.findById(rs.getString("course_id"));
				if (m.isPresent())
					courses.add(m.get());
			}
			return courses;
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
