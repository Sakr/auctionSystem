/**
 *
 */
package fr.auctionSystem.interfaces;

import fr.auctionSystem.bean.AuctionBean;


/**
 * @author Sakr
 *
 */
public interface SellerRole extends BuyerRole {
	/**creer une enchere **/
	public AuctionBean createAuction();
	/**publier cette enchere  **/
	public void postAuction(AuctionBean auction);
	/**preciser un prix minimum pour son enchere**/
	public void fixMinimumPrice(AuctionBean auction);
	/**annuler une enchere**/
	public void cancelAuction(AuctionBean auction);
	//TODO ALert 
	
}
