/**
 * 
 */
package fr.auctionSystem.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.ObjectBean;
import fr.auctionSystem.classes.AuctionSystem;
import fr.auctionSystem.classes.User;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Clock;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author david
 *
 */
public class TestSeller {

	private AuctionSystem auctionSystem;
	private ObjectBean product;
	private User seller;
	private User buyer;
	private Clock creationClock;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("=== setUp ===");
		auctionSystem = new AuctionSystem();
		creationClock = new Clock(new Date());
		product = new ObjectBean("Nexus 5", "Super smartphone");
		auctionSystem.createUser("toto", "Glen", "Fiddich", RoleEnum.SELLER);
		seller = auctionSystem.getUserByLogin("toto");
		auctionSystem.createUser("tata", "Ballentine", "Darmore", RoleEnum.BUYER);
		buyer = auctionSystem.getUserByLogin("tata");
		
	}

	@Test
	public void testCreateAuction() {
		System.out.println("=== testCreateAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		assertNotNull(auction);
	}
	
	@Test
	public void testPublishAuction() {
		System.out.println("=== testPublishAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		assertEquals(auction.getState(), AuctionStateEnum.PUBLISHED);
	}
	
	@Test
	public void testDoOfferOnMyOffer() {
		System.out.println("=== testDoOfferOnMyOffer ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		assertFalse(seller.issueOffer(auction, new Long(60)));
	}
	
	@Test
	public void testVisiblePublishedAuction() {
		System.out.println("=== testVisiblePublishedAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		assertTrue(auctionSystem.getListOfVisilbleAuctionForUser(seller).containsValue(auction));
	}
	
	@Test
	public void testSetMinimumPriceOnAuction() {
		System.out.println("=== testSetMinimumPriceOnAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.specifyMinimumPriceForAuction(auction, new Long(40));
		assertEquals(new Long(40), auction.getMinimumPrice());
		seller.postAuction(auction);
		seller.specifyMinimumPriceForAuction(auction, new Long(30));
		assertEquals(new Long(40), auction.getMinimumPrice());
	}
	
	@Test
	public void testVisibleMinimumPriceAuction() {
		System.out.println("=== testVisibleMinimumPriceAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		assertEquals(new Long(50), seller.getMinimumPriceOfAuction(auction));
	}
	
	@Test
	public void testSetReservePriceOnAuction() {
		System.out.println("=== testSetReservePriceOnCreatedAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.specifyReservePriceForAuction(auction, new Long(40));
		assertEquals(new Long(40), auction.getReservePrice());
		seller.postAuction(auction);
		seller.specifyReservePriceForAuction(auction, new Long(30));
		assertEquals(new Long(40), auction.getReservePrice());
	}
	
	@Test
	public void testVisibleReservePriceAuction() {
		System.out.println("=== testVisibleReservePriceAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		assertEquals(new Long(80), seller.getReservePriceForAuction(auction));
		assertEquals(null, buyer.getReservePriceForAuction(auction));
	}
	
	@Test
	public void testCancelAuction() {
		System.out.println("=== testCancelAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		seller.cancelAuction(auction);
		assertEquals(AuctionStateEnum.CANCELED, auction.getState());
	}
	
	@Test
	public void testCancelOnReachedReservePriceAuction() {
		System.out.println("=== testCancelOnReachedReservePriceAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.issueOffer(auction, new Long(80));
		seller.cancelAuction(auction);
		assertEquals(AuctionStateEnum.PUBLISHED, auction.getState());
	}
	
	@Test
	public void testVisibleCreatedAuction() {
		System.out.println("=== testVisibleCreatedAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		assertTrue(auctionSystem.getListOfVisilbleAuctionForUser(seller).containsValue(auction));
	}
	
	@Test
	public void testVisibleCanceledAuction() {
		System.out.println("=== testVisibleCanceledAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.cancelAuction(auction);
		assertTrue(auctionSystem.getListOfVisilbleAuctionForUser(seller).containsValue(auction));
	}
	
	@Test
	public void testAutomaticAlertOnOffer() {
		System.out.println("=== testAutomaticAlertOnOffer ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.issueOffer(auction, new Long(51));
		assertFalse(seller.getListAlertBean().isEmpty());
	}
	

}
