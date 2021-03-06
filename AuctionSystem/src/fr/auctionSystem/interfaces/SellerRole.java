package fr.auctionSystem.interfaces;

import java.util.Date;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.ObjectBean;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Clock;

/**
 * @author 
 * Les methodes de l'acheteur
 */
public interface SellerRole {
	//creer une enchere 
	public AuctionBean createAuction(AuctionStateEnum state, Date deadLine,Clock creationClock, Long minimumPrice, Long reservePrice, ObjectBean product);
	//publier cette enchere 
	public boolean postAuction(AuctionBean auction);
	//annuler une enchere
	public boolean cancelAuction(AuctionBean auction);
	//preciser un prix minimum pour son enchere
	public boolean specifyMinimumPriceForAuction(AuctionBean auction, Long minimumPrice);
	//preciser le prix de reserve 
	public boolean specifyReservePriceForAuction(AuctionBean auction, Long reservePrice);
	//preciser le prix de reserve 
	public Long getReservePriceForAuction(AuctionBean auction);
}
