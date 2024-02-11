package uo.ri.cws.application.service.invoice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Cashier It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface InvoicingService {

    /**
     * Create a new invoice billing the workorders in the argument The new
     * invoice will be in NOT_YET_PAID state, the workorders will be marked as
     * INVOICED
     * 
     * @param the list of workorder ids to bill
     * @throws BusinessException if 
     * 		- the state of any of the workorders is not FINISHED 
     * 		- any of the workorders does not exist
     * 
     * @throws IllegalArgumentException if 
     * 		- list is null o empty 
     * 		- any of the strings in the list is empty or null
     */
    InvoiceDto createInvoiceFor(List<String> workOrderIds)
	    throws BusinessException;

    /**
     * Returns a list with info of all the work orders of all the client's
     * vehicles
     * 
     * @param dni of the client
     * @return a list of InvoicingWorkOrderDto or empty list if there is none
     * @throws BusinessException DOES NOT
     */
    List<WorkOrderForInvoicingDto> findWorkOrdersByClientDni(String dni)
	    throws BusinessException;

    /**
     * Find FINISHED BUT NOT INVOICED workorders due to a client with certain
     * dni.
     * 
     * @param the dni
     * @return the list of workorders or an empty list in there is none
     * @throws BusinessException if client with this dni does not exist
     * @throws llegalArgumentException if dni is null or empty
     */
    List<WorkOrderForInvoicingDto> findNotInvoicedWorkOrdersByClientDni(
	    String dni) throws BusinessException;

    /**
     * Returns a list with info of all the work orders of a vehicle
     * 
     * @param plate, the plate number of the vehicle
     * @return a list of InvoicingWorkOrderDto or empty list if there is none
     * @throws llegalArgumentException if plate is null or empty
     * @throws BusinessException DOES NOT
     */
    List<WorkOrderForInvoicingDto> findWorkOrdersByPlateNumber(String plate)
	    throws BusinessException;

    /**
     * @param number, of the invoice
     * @return the InvoiceDto with the data of the invoice
     * @throws llegalArgumentException if number is null
     * @throws BusinessException DOES NOT
     */
    Optional<InvoiceDto> findInvoiceByNumber(Long number)
	    throws BusinessException;

    /**
     * @param dni of the client
     * @return the list of the PaymentMeanDto of a client represented by the dni
     *         or an empty list if none
     * @throws llegalArgumentException if dni is null or empty
     * @throws BusinessException DOES NOT
     */
    List<PaymentMeanDto> findPayMeansByClientDni(String dni)
	    throws BusinessException;

    /**
     * Creates the charges against the indicated payment means (with the amount
     * indicated) and then pass the invoice to the PAID state.
     *
     * @param invoiceId, the id of the invoice to be paid
     * @param charges,   a List of ChargeDto whose: - Key (String) represents
     *                   the payment mean id, and - Value (Double) represents
     *                   the amount to be charged to the payment mean
     * @throws IllegalArgumentException if invoiceId is null or empty 
     * 		charges is null
     * @throws BusinessException if 
     * 		- there is no invoice for the invoiceId provided 
     * 		- the indicated invoice is already in PAID state 
     * 		- does not exist any of the payment means indicated by the id 
     * 		- the addition of all amounts charged to each payment mean 
     * 			does not equals the amount of the invoice with a precision 
     * 			of two cents ( Math.abs( amount - amount) <= 0.01 ) 
     * 		- any of the payment means cannot be used to pay the specified amount: 
     * 			- For a CreditCard, if the current date is after the validThough date 
     * 			- For a Voucher, if it has not enough available for the amount 
     * 			- For Cash there is no constraint, cash can be used always
     *
     *    Note (JUST FOR JPA IMPLEMENTATION): the domain model does not have 
     *       the proper design to do it polymorphically,
     *    THUS
     *       add a public abstract boolean canPay( amount ); method 
     *       to PaymentMean class and the proper specialization on the
     *       child classes
     */
    void settleInvoice(String invoiceId, List<ChargeDto> charges)
	    throws BusinessException;
	
	public class InvoiceDto {

		public String id;		// the surrogate id (UUID)
		public long version;
		
		public double amount;	// amount (money) vat included
		public double vat;		// amount of vat (money, not percentage)
		public long number;		// the invoice identity, a sequential number
		public LocalDate date;	// of the invoice
		public String state;	// the state as in InvoiceState

	}
	
	public abstract class PaymentMeanDto {
		public String id;
		public long version;
		
		public String clientId;
		public Double accumulated;
	}
	
	public class CardDto extends PaymentMeanDto {
		public String cardNumber;
		public LocalDate cardExpiration;
		public String cardType;
	}
	
	public class CashDto extends PaymentMeanDto {
	}
	
	public class VoucherDto extends PaymentMeanDto {
		public String code;
		public String description;
		public Double available;
	}

	public class WorkOrderForInvoicingDto {
		public String id;
		public long version;

		public String vehicleId;
		public String description;
		public LocalDateTime date;
		public double amount;
		public String state;

		// might be null
		public String mechanicId;
		public String invoiceId;
	}
	
	public class ChargeDto {
		public String id;
		public long version;

		public String invoiceId;
		public String paymentMeanId;
		public double amount;
	}

}
