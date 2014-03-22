/**
 *
 */
package fr.auctionSystem.interfaces;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Clock;


/**
 * @author Sakr
 *
 */
public interface SellerRole extends BuyerRole {
	/**creer une enchere **/
	public AuctionBean createAuction(AuctionStateEnum state, Clock deadLine, Long minimumPrice, Long reservePrice);
	/**publier cette enchere  **/
	public boolean postAuction(AuctionBean auction);
	/**annuler une enchere**/
	public boolean cancelAuction(AuctionBean auction);
	/**preciser un prix minimum pour son encher**/
	//public boolean specifyMinimumPriceForAuction(AuctionBean auction, Long minimumPrice);
	
}
