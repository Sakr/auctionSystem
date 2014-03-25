/**
 * 
 */
package fr.auctionSystem.interfaces;

import fr.auctionSystem.bean.AuctionBean;

/**
 * @author david
 *
 */
public interface BuyerRole extends BuyerAlerter {
	/**emettre une offre pour une enchere publiee**/
	public boolean issueOffer(AuctionBean auction, Long price);
	/**recupere le prix minimum pour une encher donnée**/
	public Long getMinimumPriceOfAuction(AuctionBean auction);
}
