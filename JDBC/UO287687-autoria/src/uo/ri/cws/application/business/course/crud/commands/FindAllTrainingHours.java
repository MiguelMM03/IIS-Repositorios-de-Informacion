package uo.ri.cws.application.business.course.crud.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import uo.ri.cws.application.business.course.CourseService.TrainingHoursRow;
import uo.ri.cws.application.business.course.assembler.CourseAssembler;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.business.vehicletype.assembler.VehicleTypeAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.dedication.DedicationGateway;
import uo.ri.cws.application.persistence.enrollment.EnrollmentGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway;

public class FindAllTrainingHours implements Command<List<TrainingHoursRow>> {

	private VehicleTypeGateway vtg = PersistenceFactory.forVehicleType();
	private DedicationGateway dg = PersistenceFactory.forDedication();
	private MechanicGateway mg = PersistenceFactory.forMechanic();
	private EnrollmentGateway eg = PersistenceFactory.forEnrollment();

	@Override
	public List<TrainingHoursRow> execute() throws BusinessException {
		List<VehicleTypeBLDto> vehiclesTypes = VehicleTypeAssembler
				.toVehicleTypeDtoList(vtg.findAll());
		List<MechanicBLDto> mechanics = MechanicAssembler
				.toDtoList(mg.findAll());
		List<TrainingHoursRow> lista = new ArrayList<TrainingHoursRow>();
		for (MechanicBLDto mechanic : mechanics) {
			Map<VehicleTypeBLDto, Double> horasPorTipoDeVehiculo = new HashMap<VehicleTypeBLDto, Double>();
			for (VehicleTypeBLDto vehicleType : vehiclesTypes) {
				horasPorTipoDeVehiculo.put(vehicleType,
						calculateHoursAttendancePerMechanicAndVehicleType(
								mechanic.id, vehicleType));
			}

			for (Entry<VehicleTypeBLDto, Double> entry : horasPorTipoDeVehiculo
					.entrySet()) {
				TrainingHoursRow t = new TrainingHoursRow();
				t.vehicleTypeName = entry.getKey().name;
				t.enrolledHours = (int) entry.getValue().doubleValue();
				t.mechanicFullName = mechanic.name + " " + mechanic.surname;
				if (t.enrolledHours > 0) {
					lista.add(t);
				}
			}
		}

		return lista;
	}

	private Double calculateHoursAttendancePerMechanicAndVehicleType(String m,
			VehicleTypeBLDto vehicleType) {
		double hours = 0;
		List<CourseBLDto> coursesMechanic = CourseAssembler
				.toCourseDtoList(eg.findCoursesByMechanic(m));
		for (CourseBLDto course : coursesMechanic) {
			int attendance = eg.findAttendanceByCourseAndMechanic(course.id, m);
			int percentageForVehicleTypeInCourse = dg
					.findPercentageForVehicleTypeInCourse(vehicleType.id,
							course.id);
			hours += course.hours * percentageForVehicleTypeInCourse / 100.0
					* attendance / 100.0;
		}
		return hours;
	}

}
