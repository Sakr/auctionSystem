/**
 * 
 */
package fr.auctionSystem.interfaces;

import fr.auctionSystem.bean.AuctionBean;

/**
 * @author Sakr
 *
 */
public interface BuyerRole extends BuyerAlerter{
	/**emettre une offre pour une enchere publiee**/
	public boolean issueOffer(AuctionBean auction, Long price);
}
