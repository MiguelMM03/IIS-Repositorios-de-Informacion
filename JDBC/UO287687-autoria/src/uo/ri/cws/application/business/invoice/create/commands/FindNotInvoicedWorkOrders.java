package uo.ri.cws.application.business.invoice.create.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoiceService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.invoice.assembler.InvoiceAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.ClientGateway.ClientDALDto;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;
import util.assertion.Argument;

public class FindNotInvoicedWorkOrders
		implements Command<List<WorkOrderForInvoicingBLDto>> {
	private String clienteDni;
	private ClientGateway cg = PersistenceFactory.forClient();
	private VehicleGateway vg = PersistenceFactory.forVehicle();

	public FindNotInvoicedWorkOrders(String clientDNI) {
		Argument.isNotEmpty(clientDNI);
		this.clienteDni = clientDNI;
	}

	private String checkClient(String clientDNI) throws BusinessException {
		Optional<ClientDALDto> client = cg.findByDni(clienteDni);
		if (client.isEmpty()) {
			throw new BusinessException("Client must exist");
		}
		return client.get().id;
	}

	@Override
	public List<WorkOrderForInvoicingBLDto> execute() throws BusinessException {
		String idCliente = checkClient(clienteDni);
		List<String> vehicleIds = getVehicleIds(vg.findByClient(idCliente));

		List<WorkOrderForInvoicingBLDto> workOrders = InvoiceAssembler
				.toInvoicingWorkOrderList(PersistenceFactory.forWorkOrder()
						.findNotInvoicedForVehicles(vehicleIds));
		return workOrders;
	}

	private List<String> getVehicleIds(List<VehicleDALDto> vehicles) {
		List<String> vehicleIds = new ArrayList<String>();

		for (VehicleDALDto vehicle : vehicles) {
			vehicleIds.add(vehicle.id);
		}

		return vehicleIds;
	}
}
