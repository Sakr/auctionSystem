package fr.auctionSystem.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.auctionSystem.classes.AuctionSystem;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author 
 *
 */
public class TestAuctionSystem {
	private AuctionSystem auctionSystem;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		auctionSystem = new AuctionSystem();
		
		
		
	}

	@Test
	public void testCreateUser() {
		assertTrue(auctionSystem.createUser("toto", "Glen", "Fiddich", RoleEnum.SELLER));
		assertTrue(auctionSystem.createUser("tata", "Johnnie", "Campbell", RoleEnum.BUYER));
		assertTrue(auctionSystem.createUser("tutu", "Ballentine", "Darmore", RoleEnum.SELLER_BUYER));
	}

}
