/**
 *
 */
package fr.auctionSystem.interfaces;


/**
 * @author Sakr
 *
 */
public interface SellerRole extends BuyerRole {
	/**creer une enchere **/
	public void createAuction();
	/**publier cette enchere  **/
	public void postAuction();
	/**preciser un prix minimum pour son enchere**/
	public void fixMinimumPrice();
	/**annuler une enchere**/
	public void cancelAuction();
	
}
