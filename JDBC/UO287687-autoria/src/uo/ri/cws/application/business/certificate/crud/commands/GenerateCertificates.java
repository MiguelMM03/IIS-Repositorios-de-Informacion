package uo.ri.cws.application.business.certificate.crud.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.business.certificate.assembler.CertificateAssembler;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import uo.ri.cws.application.business.course.assembler.CourseAssembler;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.business.vehicletype.assembler.VehicleTypeAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.certificate.CertificateGateway;
import uo.ri.cws.application.persistence.dedication.DedicationGateway;
import uo.ri.cws.application.persistence.enrollment.EnrollmentGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway;

public class GenerateCertificates implements Command<Integer> {

	private CertificateGateway certgateway = PersistenceFactory
			.forCertificate();
	private MechanicGateway mg = PersistenceFactory.forMechanic();
	private EnrollmentGateway enrollgate = PersistenceFactory.forEnrollment();
	private VehicleTypeGateway vtg = PersistenceFactory.forVehicleType();
	private DedicationGateway dg = PersistenceFactory.forDedication();

	@Override
	public Integer execute() throws BusinessException {
		List<CertificateBLDto> certificates = findCertificates();
		int count = 0;
		for (CertificateBLDto cert : certificates) {
			certgateway.add(CertificateAssembler.BLDtoCertificateDALDto(cert));
			count++;
		}
		return count;
	}

	private List<CertificateBLDto> findCertificates() {
		List<CertificateBLDto> certificates = new ArrayList<CertificateBLDto>();
		List<MechanicBLDto> mechanics = MechanicAssembler
				.toDtoList(mg.findAll());
		List<VehicleTypeBLDto> vehiclesTypes = VehicleTypeAssembler
				.toVehicleTypeDtoList(vtg.findAll());
		for (MechanicBLDto m : mechanics) {

			Map<VehicleTypeBLDto, Integer> horasPorTipoDeVehiculo = new HashMap<VehicleTypeBLDto, Integer>();
			for (VehicleTypeBLDto vehicleType : vehiclesTypes) {
				horasPorTipoDeVehiculo.put(vehicleType,
						calculateHoursPerMechanicAndVehicleTypeInPassedCourses(
								m, vehicleType));
			}
			for (Map.Entry<VehicleTypeBLDto, Integer> entry : horasPorTipoDeVehiculo
					.entrySet()) {
				if (entry.getKey().minTrainigHours <= entry.getValue()) {
					CertificateBLDto certToAdd = new CertificateBLDto();
					certToAdd.id = UUID.randomUUID().toString();
					certToAdd.mechanic = m;
					certToAdd.vehicleType = entry.getKey();
					certToAdd.version = 1L;
					certToAdd.obtainedAt = LocalDate.now();
					if (!existCertificate(certToAdd))
						certificates.add(certToAdd);
				}
			}
		}

		return certificates;
	}

	private Integer calculateHoursPerMechanicAndVehicleTypeInPassedCourses(
			MechanicBLDto m, VehicleTypeBLDto vehicleType) {
		int hours = 0;
		List<CourseBLDto> coursesMechanic = findCoursesPassedByMechanic(m);
		for (CourseBLDto course : coursesMechanic) {
			int attendance = enrollgate
					.findAttendanceByCourseAndMechanic(course.id, m.id);
			int percentageForVehicleTypeInCourse = dg
					.findPercentageForVehicleTypeInCourse(vehicleType.id,
							course.id);
			hours += course.hours * percentageForVehicleTypeInCourse / 100.0
					* attendance / 100.0;
		}
		return hours;
	}

	private List<CourseBLDto> findCoursesPassedByMechanic(MechanicBLDto m) {
		return CourseAssembler
				.toCourseDtoList(enrollgate.findCoursesPassedByMechanic(m.id));
	}

	private boolean existCertificate(CertificateBLDto c) {
		return certgateway.findCertificatesByVehicleTypeIdAndMechanic(
				c.vehicleType.id, c.mechanic.id).isPresent();
	}

}
