package uo.ri.cws.application.persistence;

import uo.ri.cws.application.persistence.certificate.CertificateGateway;
import uo.ri.cws.application.persistence.certificate.impl.CertificateGatewayImpl;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.impl.ClientGatewayImpl;
import uo.ri.cws.application.persistence.course.CourseGateway;
import uo.ri.cws.application.persistence.course.impl.CourseGatewayImpl;
import uo.ri.cws.application.persistence.dedication.DedicationGateway;
import uo.ri.cws.application.persistence.dedication.impl.DedicationGatewayImpl;
import uo.ri.cws.application.persistence.enrollment.EnrollmentGateway;
import uo.ri.cws.application.persistence.enrollment.impl.EnrollmentGatewayImpl;
import uo.ri.cws.application.persistence.intervention.InterventionGateway;
import uo.ri.cws.application.persistence.intervention.impl.InterventionGatewayImpl;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.impl.InvoiceGatewayImpl;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.impl.MechanicGatewayImpl;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.impl.VehicleGatewayImpl;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway;
import uo.ri.cws.application.persistence.vehicletype.impl.VehicleTypeGatewayImpl;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.impl.WorkOrderGatewayImpl;

public class PersistenceFactory {

	public static MechanicGateway forMechanic() {
		return new MechanicGatewayImpl();
	}

	public static WorkOrderGateway forWorkOrder() {
		return new WorkOrderGatewayImpl();
	}

	public static InvoiceGateway forInvoice() {
		return new InvoiceGatewayImpl();
	}

	public static ClientGateway forClient() {
		return new ClientGatewayImpl();
	}

	public static VehicleGateway forVehicle() {
		return new VehicleGatewayImpl();
	}

	public static VehicleTypeGateway forVehicleType() {
		return new VehicleTypeGatewayImpl();
	}

	public static CertificateGateway forCertificate() {
		return new CertificateGatewayImpl();
	}

	public static InterventionGateway forIntervention() {
		return new InterventionGatewayImpl();
	}

	public static CourseGateway forCourse() {
		return new CourseGatewayImpl();
	}

	public static EnrollmentGateway forEnrollment() {
		return new EnrollmentGatewayImpl();
	}

	public static DedicationGateway forDedication() {
		return new DedicationGatewayImpl();
	}

}
