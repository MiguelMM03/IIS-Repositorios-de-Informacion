package uo.ri.cws.application.service.course.impl.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.course.CourseCrudService.TrainingForVehicleTypeReportLineDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class FindTrainingSortedByVehicleTypeAndMechanic
		implements Command<List<TrainingForVehicleTypeReportLineDto>> {

	@Override
	public List<TrainingForVehicleTypeReportLineDto> execute()
			throws BusinessException {
		List<TrainingForVehicleTypeReportLineDto> result = new ArrayList<>();
		List<VehicleType> vehicleTypes = Factory.repository.forVehicleType()
				.findAll();
		List<Mechanic> mechanics = Factory.repository.forMechanic().findAll();
		for (VehicleType vehicleType : vehicleTypes) {
			for (Mechanic mechanic : mechanics) {
				TrainingForVehicleTypeReportLineDto t = 
						new TrainingForVehicleTypeReportLineDto();
				t.vehicleTypeName = vehicleType.getName();
				t.enrolledHours = 
					calculateHoursPerMechanicAndVehicleTypeInFinishedCourses(
					mechanic, vehicleType);
				t.mechanicFullName = mechanic.getSurname() + ", "
						+ mechanic.getName();
				if (t.enrolledHours > 0) {
					result.add(t);
				}
			}
		}

		return result;
	}

	private Integer calculateHoursPerMechanicAndVehicleTypeInFinishedCourses(
			Mechanic m, VehicleType vehicleType) {
		int hours = 0;
		List<Course> coursesByMechanic = m.getEnrollments().stream()
				.map(e -> e.getCourse())
				.filter(e -> e.getEndDate().isBefore(LocalDate.now())).toList();
		for (Course course : coursesByMechanic) {
			hours += course.getEnrollments().stream()
					.filter(e -> e.getMechanic().equals(m))
					.map(a -> a.getAttendedHoursFor(vehicleType))
					.reduce(0, (a, b) -> a + b);
		}
		return hours;
	}

}
