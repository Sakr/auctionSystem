/**
 *
 */
package fr.auctionSystem.interfaces;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Horloge;


/**
 * @author Sakr
 *
 */
public interface SellerRole extends BuyerRole {
	/**creer une enchere **/
	public AuctionBean createAuction(AuctionStateEnum state, Horloge deadLine, Long minimumPrice, Long reservePrice);
	/**publier cette enchere  **/
	public boolean postAuction(AuctionBean auction);
	/**preciser un prix minimum pour son enchere**/
	public void fixMinimumPrice(AuctionBean auction);
	/**annuler une enchere**/
	public void cancelAuction(AuctionBean auction);
	//TODO ALert 
	
}
