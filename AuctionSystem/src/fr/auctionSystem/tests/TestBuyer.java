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

public class TestBuyer {

	private AuctionSystem auctionSystem;
	private ObjectBean product;
	private User seller;
	private User buyer;
	private User buyer1;
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
		auctionSystem.createUser("toto", "Glen", "Fiddich", RoleEnum.SELLER_BUYER);
		seller = auctionSystem.getUserByLogin("toto");
		auctionSystem.createUser("tata", "Ballentine", "Darmore", RoleEnum.SELLER_BUYER);
		buyer = auctionSystem.getUserByLogin("tata");
		auctionSystem.createUser("tutu", "Johnnie", "Campbell", RoleEnum.SELLER_BUYER);
		buyer1 = auctionSystem.getUserByLogin("tutu");
		
	}

	@Test
	public void testDoOffer() {
		System.out.println("=== testDoOffer ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		assertFalse(buyer.issueOffer(auction, new Long(60)));
		seller.postAuction(auction);
		assertTrue(buyer.issueOffer(auction, new Long(60)));
	}
	
	@Test
	public void testVisiblePublishedAuction() {
		System.out.println("=== testVisiblePublishedAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		assertTrue(auctionSystem.getListOfVisilbleAuctionForUser(buyer).containsValue(auction));
	}
	
	@Test
	public void testDoOfferWithBadMinimumPrice() {
		System.out.println("=== testDoOfferWithBadMinimumPrice ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		assertFalse(buyer.issueOffer(auction, new Long(40)));
	}
	
	@Test
	public void testVisibleMinimumPriceAuction() {
		System.out.println("=== testVisibleMinimumPriceAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		assertEquals(new Long(50), buyer.getMinimumPriceOfAuction(auction));
	}
	
	@Test
	public void testVisibleCreatedAuction() {
		System.out.println("=== testVisibleCreatedAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		assertFalse(auctionSystem.getListOfVisilbleAuctionForUser(buyer).containsValue(auction));
	}
	
	@Test
	public void testVisibleCanceledAuction() {
		System.out.println("=== testVisibleCanceledAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.issueOffer(auction, new Long(60));
		seller.cancelAuction(auction);
		assertTrue(auctionSystem.getListOfVisilbleAuctionForUser(buyer).containsValue(auction));
		assertFalse(auctionSystem.getListOfVisilbleAuctionForUser(buyer1).containsValue(auction));
	}
	
	@Test
	public void testReachedDeadLineAuction() {
		System.out.println("=== testReachedDeadLineAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.issueOffer(auction, new Long(60));
		buyer1.issueOffer(auction, new Long(80));
		creationClock.setCurrentDate(Clock.addDays(creationClock.getCurrentDate(), 3));
	}
	
	@Test
	public void testReachedDeadLineAuctionWithBadFinalPrice() {
		System.out.println("=== testReachedDeadLineAuctionWithBadFinalPrice ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.issueOffer(auction, new Long(60));
		buyer1.issueOffer(auction, new Long(70));
		creationClock.setCurrentDate(Clock.addDays(creationClock.getCurrentDate(), 3));
	}
	
	@Test
	public void testReachedReservePriceAuctionAlert() {
		System.out.println("=== testReachedReservePriceAuctionAlert ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.addPriceReserveAlert(true, auction);
		buyer1.issueOffer(auction, new Long(60));
		buyer.issueOffer(auction, new Long(70));
		buyer1.issueOffer(auction, new Long(80));
		assertFalse(buyer.getListAlertBean().isEmpty());
	}
	
	@Test
	public void testCancelAuctionAlert() {
		System.out.println("=== testCancelAuctionAlert ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.addAuctionCanceledBySellerAlert(true, auction);
		seller.cancelAuction(auction);
		assertFalse(buyer.getListAlertBean().isEmpty());
	}
	
	@Test
	public void testGreaterOfferOnAuctionAlert() {
		System.out.println("=== testGreaterOfferOnAuctionAlert ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.addAnotherGreaterOfferAlert(true, auction);
		buyer.issueOffer(auction, new Long(50));
		buyer1.issueOffer(auction, new Long(60));
		buyer.issueOffer(auction, new Long(70));
		buyer1.issueOffer(auction, new Long(80));
		assertFalse(buyer.getListAlertBean().isEmpty());
	}
	
	@Test
	public void testDisableReachedReservePriceAuctionAlert() {
		System.out.println("=== testDisableReachedReservePriceAuctionAlert ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.addPriceReserveAlert(false, auction);
		buyer1.issueOffer(auction, new Long(80));
		assertTrue(buyer.getListAlertBean().isEmpty());
	}
	
	@Test
	public void testDisableCancelAuctionAlert() {
		System.out.println("=== testDisableCancelAuctionAlert ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.addAuctionCanceledBySellerAlert(false, auction);
		seller.cancelAuction(auction);
		assertTrue(buyer.getListAlertBean().isEmpty());
	}
	
	@Test
	public void testDisableGreaterOfferOnAuctionAlert() {
		System.out.println("=== testDisableGreaterOfferOnAuctionAlert ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.addAnotherGreaterOfferAlert(false, auction);
		buyer.issueOffer(auction, new Long(51));
		buyer1.issueOffer(auction, new Long(60));
		buyer.issueOffer(auction, new Long(70));
		buyer1.issueOffer(auction, new Long(80));
		assertTrue(buyer.getListAlertBean().isEmpty());
	}
	
	@Test
	public void testDisableAllAlertOnAuction() {
		System.out.println("=== testDisableAllAlertOnAuction ===");
		AuctionBean auction = seller.createAuction(AuctionStateEnum.CREATED, Clock.addDays(new Date(), 2), creationClock, new Long(50), new Long(80), product);
		seller.postAuction(auction);
		buyer.addPriceReserveAlert(true, auction);
		buyer.addAnotherGreaterOfferAlert(true, auction);
		boolean disableReturn = buyer.disableAllAlertOnAuction(auction); 
		
		buyer.issueOffer(auction, new Long(51));
		buyer1.issueOffer(auction, new Long(60));
		buyer.issueOffer(auction, new Long(70));
		buyer1.issueOffer(auction, new Long(80));
		assertTrue(disableReturn);
	}

}
