package uo.ri.cws.application;

import uo.ri.cws.application.service.certificate.CertificateService;
import uo.ri.cws.application.service.client.ClientCrudService;
import uo.ri.cws.application.service.client.ClientHistoryService;
import uo.ri.cws.application.service.course.CourseCrudService;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.sparepart.SparePartCrudService;
import uo.ri.cws.application.service.vehicle.VehicleCrudService;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderService;

public interface ServiceFactory {

	// Manager use cases
	VehicleTypeCrudService forVehicleTypeCrudService();
	MechanicCrudService forMechanicCrudService();
	SparePartCrudService forSparePartCrudService();

	CourseCrudService forCourseCrudService();
	CourseEnrollmentService forCourseEnrollmentService();
	CertificateService forCertificateService();

	// Foreman and Mechanic use cases
	WorkOrderService forWorkOrderService();
	VehicleCrudService forVehicleCrudService();
	ClientCrudService forClientCrudService();
	ClientHistoryService forClientHistoryService();

	// Cashier use cases
	InvoicingService forInvoicingService();

}
