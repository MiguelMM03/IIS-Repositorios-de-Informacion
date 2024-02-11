package uo.ri.cws.application.persistence.certificate.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.certificate.CertificateGateway.CertificateDALDto;

public class CertificateAssembler {

	public static CertificateDALDto toCertificateDALDto(ResultSet m) throws SQLException {
		CertificateDALDto c = new CertificateDALDto();
		c.id = m.getString("id");
		c.version = m.getLong("version");
		c.mechanicId = m.getString("mechanic_id");
		c.vehicleTypeId = m.getString("vehicleType_id");
		c.date = m.getDate("date").toLocalDate();
		return c;
	}

	public static Optional<CertificateDALDto> toOptionalCertificateDALDto(ResultSet m) throws SQLException {
		if (m.next()) {
			return Optional.of(toCertificateDALDto(m));
		} else
			return Optional.ofNullable(null);
	}

	public static List<CertificateDALDto> toCertificateDALDtoList(ResultSet rs) throws SQLException {
		List<CertificateDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(toCertificateDALDto(rs));
		}

		return res;
	}
}
