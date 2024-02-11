package uo.ri.cws.application.business;

import uo.ri.cws.application.business.certificate.CertificateService;
import uo.ri.cws.application.business.certificate.crud.CertificateServiceImpl;
import uo.ri.cws.application.business.course.CourseService;
import uo.ri.cws.application.business.course.crud.CourseServiceImpl;
import uo.ri.cws.application.business.enrollment.EnrollmentService;
import uo.ri.cws.application.business.enrollment.crud.EnrollmentServiceImpl;
import uo.ri.cws.application.business.invoice.InvoiceService;
import uo.ri.cws.application.business.invoice.create.InvoiceServiceImpl;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.MechanicServiceImpl;
import uo.ri.cws.application.business.vehicle.VehicleService;
import uo.ri.cws.application.business.vehicle.crud.VehicleServiceImpl;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService;
import uo.ri.cws.application.business.vehicletype.crud.VehicleTypeServiceImpl;
import uo.ri.cws.application.business.workorder.WorkOrderService;
import uo.ri.cws.application.business.workorder.crud.WorkOrderServiceImpl;

public class BusinessFactory {

	public static MechanicService forMechanicService() {
		return new MechanicServiceImpl();
	}

	public static InvoiceService forInvoicingService() {
		return new InvoiceServiceImpl();
	}

	public static WorkOrderService forWorkOrderService() {
		return new WorkOrderServiceImpl();

	}

	public static VehicleTypeService forVehicleTypeService() {
		return new VehicleTypeServiceImpl();
	}

	public static VehicleService forVehicleService() {
		return new VehicleServiceImpl();
	}

	public static CertificateService forCertificateService() {
		return new CertificateServiceImpl();
	}

	public static CourseService forCourseService() {
		return new CourseServiceImpl();
	}

	public static EnrollmentService forEnrollmentService() {
		return new EnrollmentServiceImpl();
	}

}
