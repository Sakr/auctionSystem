/**
 * 
 */
package fr.auctionSystem.interfaces;

import fr.auctionSystem.bean.AuctionBean;

/**
 * @author Sakr
 *
 */
public interface BuyerRole {
	/**emettre une offre pour une enchere publiee**/
	public void issueOffer(AuctionBean auction);
}
