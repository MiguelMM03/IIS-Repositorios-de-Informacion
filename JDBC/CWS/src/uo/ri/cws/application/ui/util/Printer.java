package uo.ri.cws.application.ui.util;

import java.time.format.DateTimeFormatter;
import java.util.List;

import uo.ri.cws.application.business.certificate.CertificateService.CertificateBLDto;
import uo.ri.cws.application.business.client.ClientService.ClientBLDto;
import uo.ri.cws.application.business.course.CourseService.CourseBLDto;
import uo.ri.cws.application.business.course.CourseService.TrainingForMechanicRow;
import uo.ri.cws.application.business.course.CourseService.TrainingHoursRow;
import uo.ri.cws.application.business.enrollment.EnrollmentService.EnrollmentBLDto;
import uo.ri.cws.application.business.invoice.InvoiceService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoiceService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import util.console.Console;
import util.exception.NotYetImplementedException;

public class Printer {

	public static void printMechanic(MechanicBLDto m) {
		Console.printf("\t%-36.36s %-9s %-10.10s %-25.25s %-10.2s\n", m.id, m.dni, m.name, m.surname, m.version);
	}

	public static void printMechanics(List<MechanicBLDto> list) {

		Console.printf("\t%-36s %-9s %-10s %-25s %-10s\n", "Mechanic identifier", "DNI", "Name", "Surname", "Version");
		for (MechanicBLDto m : list)
			printMechanic(m);
	}

	public static void printInvoice(InvoiceBLDto invoice) {

		double importeConIVa = invoice.total;
		double iva = invoice.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Invoice number: %d%n", invoice.number);
		Console.printf("\tDate: %1$td/%1$tm/%1$tY%n", invoice.date);
		Console.printf("\tAmount: %.2f %n", importeSinIva);
		Console.printf("\tVat: %.1f %% %n", invoice.vat);
		Console.printf("\tTotal (vat included): %.2f %n", invoice.total);
		Console.printf("\tState: %s%n", invoice.status);
	}

	public static void printInvoicingWorkOrder(WorkOrderForInvoicingBLDto arg) {
		
		Console.printf("\t%s \t%-40.40s \t%s \t%-12.12s \t%.2f\n",  
				arg.id
				, arg.description 
				, arg.date
				, arg.status
				, arg.total
			);		
	}

	public static void printInvoicingWorkOrders(List<WorkOrderForInvoicingBLDto> arg) {
		Console.printf("\t%s \t%-40.40s \t%s \t%-12.12s \t%.2f\n",  
				"Identifier", "description", "status", "total");
		for (WorkOrderForInvoicingBLDto inv : arg)
			printInvoicingWorkOrder(inv);
	}

	public static void printClients(List<ClientBLDto> clients) {
		throw new NotYetImplementedException();
		
	}

	public static void printClient(ClientBLDto dto) {
		throw new NotYetImplementedException();
		
	}

	public static void printWorkOrders(List<WorkOrderBLDto> wos) {
		for(WorkOrderBLDto wo: wos) {
			printWorkOrderDetail(wo);
		}
		
	}

	public static void printCertifiedMechanic(CertificateBLDto m) {
		Console.printf("%s %s. DNI: %s. Obtained at: %s\n", 
				m.mechanic.name,m.mechanic.surname,m.mechanic.dni,
				m.obtainedAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
	}

	public static void printWorkOrderDetail(WorkOrderBLDto wo) {
		Console.printf(
				"Id: %s\n"
				+ "\tDescripcion: %s\n"
				+ "\tFecha: %s\n"
				+ "\tAmount: %f\n"
				+ "\tStatus: %s\n"
				+ "\tMechanic id: %s"
				+ "\tInvoice id: %s"
				+ "\tVehicle id: %s"
				+ "\tVersion: %d\n",
				wo.id,
				wo.description,
				wo.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				wo.amount,
				wo.status,
				wo.mechanicId,
				wo.invoiceId,
				wo.vehicleId,
				wo.version);
		
	}

	public static void printVehicleDetail(VehicleBLDto v) {
		Console.printf(
				"Id: %s\n"
				+ "\tPlate number: %s\n"
				+ "\tMake: %s\n"
				+ "\tModel: %s\n"
				+ "\tVehicle type id: %s\n"
				+ "\tClient id: %s\n"
				+ "\tVersion: %d\n",
				v.id,
				v.plateNumber,
				v.make,
				v.model,
				v.vehicleTypeId,
				v.clientId,
				v.version);
		
	}

	public static void printVehicleTypes(List<VehicleTypeBLDto> findAllVehicleTypes) {
		for(VehicleTypeBLDto vt:findAllVehicleTypes) {
			printVehicleType(vt);
		}
		
	}

	public static void printVehicleType(VehicleTypeBLDto vt) {
		Console.printf(
				"Id: %s\n"
				+ "\tName: %s\n"
				+ "\tPrice per hour: %.2f\n"
				+ "\tMin training hours: %d\n"
				+ "\tVersion: %d\n",
				vt.id,
				vt.name,
				vt.pricePerHour,
				vt.minTrainigHours,
				vt.version);
		
	}

	public static void printCourse(CourseBLDto c) {
		// TODO Auto-generated method stub
		
	}

	public static void printAttendingMechanic(EnrollmentBLDto att) {
		// TODO Auto-generated method stub
	}

	public static void printCertificateRow(CertificateBLDto r) {
		Console.printf("\tCertificate id: %s. Vehicle type: %s. Date: %s. DNI mechanic: %s\n",
				r.id,
				r.vehicleType.name,
				r.obtainedAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				r.mechanic.dni);
	}

	public static void printTrainingHoursRow(TrainingHoursRow r) {
		Console.printf("\t%s, %d horas\n", r.mechanicFullName, r.enrolledHours);
	}

	public static void printTrainingForMechanic(TrainingForMechanicRow row) {
		Console.printf("Vehicle type: %s. Enrolled hours: %d. Attended hours: %.2f\n", 
				row.vehicleTypeName,
				row.enrolledHours,
				row.attendedHours);
	}

	
	
}