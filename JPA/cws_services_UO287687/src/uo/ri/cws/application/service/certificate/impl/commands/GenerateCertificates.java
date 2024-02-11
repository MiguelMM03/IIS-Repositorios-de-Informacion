package uo.ri.cws.application.service.certificate.impl.commands;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class GenerateCertificates implements Command<Integer> {

	public GenerateCertificates() {

	}

	@Override
	public Integer execute() throws BusinessException {
		/*Podría estar mejor implementado */
		int number = 0;
		List<Mechanic> mechanics = Factory.repository.forMechanic().findAll();
		List<VehicleType> vehicleTypes = Factory.repository.forVehicleType()
				.findAll();
		for (Mechanic mechanic : mechanics) {
			for (VehicleType vehicleType : vehicleTypes) {
				if (vehicleType
						.getMinTrainingHours() <= 
						calculateHoursPerMechanicAndVehicleTypeInPassedCourses(
								mechanic, vehicleType)) {
					List<Certificate> existing = Factory.repository
							.forCertificate()
							.findByVehicleTypeId(vehicleType.getId());
					if (existing.stream()
							.filter(c -> c.getMechanic().equals(mechanic))
							.toList().isEmpty()) {
						Certificate certToAdd = new Certificate(mechanic,
								vehicleType);
						Factory.repository.forCertificate().add(certToAdd);
						number++;
					}

				}
			}

		}
		return number;
	}

	private Integer calculateHoursPerMechanicAndVehicleTypeInPassedCourses(
			Mechanic m, VehicleType vehicleType) {
		int hours = 0;
		List<Course> coursesByMechanic = m.getEnrollments().stream()
				.filter(e -> e.isPassed()).map(e -> e.getCourse()).toList();
		for (Course course : coursesByMechanic) {
			hours += course.getEnrollments().stream()
					.filter(e -> e.getMechanic().equals(m))
					.map(a -> a.getAttendedHoursFor(vehicleType))
					.reduce(0, (a, b) -> a + b);
		}
		return hours;
	}
}
