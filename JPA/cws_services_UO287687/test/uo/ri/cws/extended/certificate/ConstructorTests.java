package uo.ri.cws.extended.certificate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class ConstructorTests {

	private VehicleType vehicleType;
	private Mechanic mechanic;

	@Before
	public void setUp() throws Exception {
		mechanic = new Mechanic("123");
		vehicleType = new VehicleType("car");
	}

	
	/**
	 * Constructor takes the date of the system 
	 */
	@Test
	public void testBasicConstructor() {
		LocalDate today = LocalDate.now();
		Certificate c = new Certificate(mechanic, vehicleType);
		
		assertEquals( today, c.getDate() );
	}
	
	/**
	 * Constructor links properly
	 */
	@Test
	public void testConstructorLinks() {
		Certificate c = new Certificate(mechanic, vehicleType);
			
		assertTrue( c.getMechanic().equals( mechanic ) );
		assertTrue( mechanic.getCertificates().contains( c ) );
	}
	
}
