package uo.ri.cws.application.business.course.crud.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import uo.ri.cws.application.business.course.CourseService.TrainingForMechanicRow;
import uo.ri.cws.application.business.course.assembler.CourseAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.business.vehicletype.assembler.VehicleTypeAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.dedication.DedicationGateway;
import uo.ri.cws.application.persistence.enrollment.EnrollmentGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway;
import util.assertion.Argument;

public class FindTrainingHoursRowForMechanic implements Command<List<TrainingForMechanicRow>> {

	private EnrollmentGateway eg = PersistenceFactory.forEnrollment();
	private VehicleTypeGateway vtg = PersistenceFactory.forVehicleType();
	private DedicationGateway dg = PersistenceFactory.forDedication();
	private String mechanicid;

	public FindTrainingHoursRowForMechanic(String mechanicid) {
		Argument.isNotEmpty(mechanicid);
		this.mechanicid = mechanicid;
	}

	@Override
	public List<TrainingForMechanicRow> execute() throws BusinessException {
		Optional<MechanicDALDto> mechanic=PersistenceFactory.forMechanic().findById(mechanicid);
		if(mechanic.isEmpty()) {
			throw new BusinessException("Mechanic doesn´t exist");
		}
		List<VehicleTypeBLDto> vehiclesTypes = VehicleTypeAssembler.toVehicleTypeDtoList(vtg.findAll());
		Map<VehicleTypeBLDto, Double> horasAtendidasPorTipoDeVehiculo = new HashMap<VehicleTypeBLDto, Double>();
		for (VehicleTypeBLDto vehicleType : vehiclesTypes) {
			horasAtendidasPorTipoDeVehiculo.put(vehicleType,
					calculateHoursAttendancePerMechanicAndVehicleType(mechanicid, vehicleType));
		}
		Map<VehicleTypeBLDto, Integer> horasPorTipoDeVehiculo = new HashMap<VehicleTypeBLDto, Integer>();
		for (VehicleTypeBLDto vehicleType : vehiclesTypes) {
			horasPorTipoDeVehiculo.put(vehicleType,
					calculateHoursEnrolledPerMechanicAndVehicleType(mechanicid, vehicleType));
		}

		List<TrainingForMechanicRow> lista = new ArrayList<TrainingForMechanicRow>();
		for (Map.Entry<VehicleTypeBLDto, Double> entry : horasAtendidasPorTipoDeVehiculo.entrySet()) {
			TrainingForMechanicRow t = new TrainingForMechanicRow();
			t.vehicleTypeName = entry.getKey().name;
			t.enrolledHours = horasPorTipoDeVehiculo.get(entry.getKey());
			t.attendedHours = entry.getValue();
			lista.add(t);
		}

		return lista;
	}

	private Integer calculateHoursEnrolledPerMechanicAndVehicleType(String m, VehicleTypeBLDto vehicleType) {
		int hours = 0;
		List<CourseBLDto> coursesMechanic = CourseAssembler.toCourseDtoList(eg.findCoursesByMechanic(m));
		for (CourseBLDto course : coursesMechanic) {
			int percentageForVehicleTypeInCourse = dg.findPercentageForVehicleTypeInCourse(vehicleType.id, course.id);
			hours += course.hours * percentageForVehicleTypeInCourse / 100.0;
		}
		return hours;
	}

	private Double calculateHoursAttendancePerMechanicAndVehicleType(String m, VehicleTypeBLDto vehicleType) {
		double hours = 0;
		List<CourseBLDto> coursesMechanic = CourseAssembler.toCourseDtoList(eg.findCoursesByMechanic(m));
		for (CourseBLDto course : coursesMechanic) {
			int attendance = eg.findAttendanceByCourseAndMechanic(course.id, m);
			int percentageForVehicleTypeInCourse = dg.findPercentageForVehicleTypeInCourse(vehicleType.id, course.id);
			hours += course.hours * percentageForVehicleTypeInCourse / 100.0 * attendance / 100.0;
		}
		return hours;
	}

}
