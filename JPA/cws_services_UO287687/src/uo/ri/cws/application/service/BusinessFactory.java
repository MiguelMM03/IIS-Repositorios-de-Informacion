package uo.ri.cws.application.service;

import uo.ri.cws.application.ServiceFactory;
import uo.ri.cws.application.service.certificate.CertificateService;
import uo.ri.cws.application.service.certificate.impl.CertificateServiceImpl;
import uo.ri.cws.application.service.client.ClientCrudService;
import uo.ri.cws.application.service.client.ClientHistoryService;
import uo.ri.cws.application.service.course.CourseCrudService;
import uo.ri.cws.application.service.course.impl.CourseCrudServiceImpl;
import uo.ri.cws.application.service.enrollment.CourseEnrollmentService;
import uo.ri.cws.application.service.enrollment.impl.CourseEnrollmentServiceImpl;
import uo.ri.cws.application.service.invoice.InvoicingService;
import uo.ri.cws.application.service.invoice.impl.InvoicingServiceImpl;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.impl.MechanicCrudServiceImpl;
import uo.ri.cws.application.service.sparepart.SparePartCrudService;
import uo.ri.cws.application.service.vehicle.VehicleCrudService;
import uo.ri.cws.application.service.vehicle.crud.VehicleCrudServiceImpl;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderService;
import uo.ri.cws.application.service.workorder.impl.WorkOrderCrudServiceImpl;

public class BusinessFactory implements ServiceFactory {

	@Override
	public MechanicCrudService forMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}

	@Override
	public InvoicingService forInvoicingService() {
		return new InvoicingServiceImpl();
	}

	@Override
	public WorkOrderService forWorkOrderService() {
		return new WorkOrderCrudServiceImpl();
	}

	@Override
	public VehicleCrudService forVehicleCrudService() {
		return new VehicleCrudServiceImpl();
	}

	@Override
	public CourseCrudService forCourseCrudService() {
		return new CourseCrudServiceImpl();
	}

	@Override
	public CourseEnrollmentService forCourseEnrollmentService() {
		return new CourseEnrollmentServiceImpl();
	}

	@Override
	public CertificateService forCertificateService() {
		return new CertificateServiceImpl();
	}

	@Override
	public VehicleTypeCrudService forVehicleTypeCrudService() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public ClientCrudService forClientCrudService() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public SparePartCrudService forSparePartCrudService() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public ClientHistoryService forClientHistoryService() {
		throw new RuntimeException("Not yet implemented");
	}

}
