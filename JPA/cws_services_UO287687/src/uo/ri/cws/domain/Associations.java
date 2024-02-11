package uo.ri.cws.domain;

public class Associations {

	public static class Own {

		public static void link(Client client, Vehicle vehicle) {
			vehicle._setCliente(client);
			client._getVehicles().add(vehicle);
		}

		public static void unlink(Client client, Vehicle vehicle) {
			client._getVehicles().remove(vehicle);
			vehicle._setCliente(null);
		}

	}

	public static class Classify {

		public static void link(VehicleType vehicleType, Vehicle vehicle) {
			vehicle._setVehicleType(vehicleType);
			vehicleType._getVehicles().add(vehicle);
		}

		public static void unlink(VehicleType vehicleType, Vehicle vehicle) {
			vehicleType._getVehicles().remove(vehicle);
			vehicle._setVehicleType(null);
		}

	}

	public static class Pay {

		public static void link(Client client, PaymentMean pm) {
			client._getPaymentMeans().add(pm);
			pm._setClient(client);

		}

		public static void unlink(Client client, PaymentMean pm) {
			pm._setClient(null);
			client._getPaymentMeans().remove(pm);

		}

	}

	public static class Fix {

		public static void link(Vehicle vehicle, WorkOrder workOrder) {
			vehicle._getWorkOrders().add(workOrder);
			workOrder._setVehicle(vehicle);
		}

		public static void unlink(Vehicle vehicle, WorkOrder workOrder) {
			vehicle._getWorkOrders().remove(workOrder);
			workOrder._setVehicle(null);
		}

	}

	public static class ToInvoice {

		public static void link(Invoice invoice, WorkOrder workOrder) {
			workOrder._setInvoice(invoice);
			invoice._getWorkOrders().add(workOrder);
		}

		public static void unlink(Invoice invoice, WorkOrder workOrder) {
			invoice._getWorkOrders().remove(workOrder);
			workOrder._setInvoice(null);
		}
	}

	public static class ToCharge {

		public static void link(PaymentMean pm, Charge charge, Invoice invoice) {
			charge._setPaymentMean(pm);
			charge._setInvoice(invoice);
			pm._getCharges().add(charge);
			invoice._getCharges().add(charge);
		}

		public static void unlink(Charge charge) {
			charge._getPaymentMean()._getCharges().remove(charge);
			charge._getInvoice()._getCharges().remove(charge);
			charge._setPaymentMean(null);
			charge._setInvoice(null);
		}

	}

	public static class Assign {

		public static void link(Mechanic mechanic, WorkOrder workOrder) {
			workOrder._setMechanic(mechanic);
			mechanic._getAssigned().add(workOrder);
		}

		public static void unlink(Mechanic mechanic, WorkOrder workOrder) {
			mechanic._getAssigned().remove(workOrder);
			workOrder._setMechanic(null);
		}

	}

	public static class Intervene {

		public static void link(WorkOrder workOrder, Intervention intervention,
				Mechanic mechanic) {
			intervention._setMechanic(mechanic);
			intervention._setWorkOrder(workOrder);
			mechanic._getInterventions().add(intervention);
			workOrder._getInterventions().add(intervention);
			
		}

		public static void unlink(Intervention intervention) {
			Mechanic mechanic=intervention._getMechanic();
			WorkOrder workOrder=intervention._getWorkOrder();
			mechanic._getInterventions().remove(intervention);
			workOrder._getInterventions().remove(intervention);
			intervention._setMechanic(null);
			intervention._setWorkOrder(null);
		}

	}

	public static class Substitute {

		public static void link(SparePart spare, Substitution sustitution,
				Intervention intervention) {
			sustitution._setIntervention(intervention);
			sustitution._setSparePart(spare);
			intervention._getSubstitutions().add(sustitution);
			spare._getSubstitutions().add(sustitution);
		}

		public static void unlink(Substitution sustitution) {
			Intervention intervention=sustitution._getIntervention();
			SparePart spare=sustitution._getSparePart();
			intervention._getSubstitutions().remove(sustitution);
			spare._getSubstitutions().remove(sustitution);
			sustitution._setIntervention(null);
			sustitution._setSparePart(null);
		}

	}
	public static class Dedicate {

		public static void link(Course course, Dedication dedication,
				VehicleType vehicleType) {
			dedication._setVehicleType(vehicleType);
			dedication._setCourse(course);
			vehicleType._getDedications().add(dedication);
			course._getDedications().add(dedication);
			
		}

		public static void unlink(Dedication dedication) {
			Course course = dedication.getCourse();
			VehicleType vehicleType = dedication.getVehicleType();
			vehicleType._getDedications().remove(dedication);
			course._getDedications().remove(dedication);
			dedication._setVehicleType(null);
			dedication._setCourse(null);
		}
	}
	
	public static class Enroll {

		public static void link(Mechanic mechanic, Enrollment enrollment,
				Course course) {
			enrollment._setCourse(course);
			enrollment._setMechanic(mechanic);
			mechanic._getEnrollments().add(enrollment);
			course._getEnrollments().add(enrollment);
		}

		public static void unlink(Enrollment enrollment) {
			Mechanic mechanic = enrollment.getMechanic();
			Course course = enrollment.getCourse();
			mechanic._getEnrollments().remove(enrollment);
			course._getEnrollments().remove(enrollment);
			enrollment._setCourse(null);
			enrollment._setMechanic(null);
		}
	}
	
	public static class Certify {

		public static void link(Mechanic mechanic, Certificate certificate,
				VehicleType vehicleType) {
			certificate._setMechanic(mechanic);
			certificate._setVehicleType(vehicleType);
			mechanic._getCertificates().add(certificate);
			vehicleType._getCertificates().add(certificate);
		}

		public static void unlink(Certificate certificate) {
			certificate.getMechanic()._getCertificates().remove(certificate);
			certificate.getVehicleType()._getCertificates().remove(certificate);
			certificate._setMechanic(null);
			certificate._setVehicleType(null);
		}

	}

}
