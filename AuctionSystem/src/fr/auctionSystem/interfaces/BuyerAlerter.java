/**
 * 
 */
package fr.auctionSystem.interfaces;

import fr.auctionSystem.bean.AuctionBean;

/**
 * @author david
 *
 */
public interface BuyerAlerter {
	/**Active l'alerte si le prix de reserve est atteint par une offre**/
	public boolean addPriceReserveAlert(boolean value,AuctionBean auctionBean);
	/**Active l'alerte si le vendeur annule une enchere.**/
	public boolean addAuctionCanceledBySellerAlert(boolean value,AuctionBean auctionBean);
	/**Active l'alerte si une offre superieure a celle emise par l'acheteur a ete emise par un autre acheteur.**/
	public boolean addAnotherGreaterOfferAlert(boolean value,AuctionBean auctionBean);
	/**Desactive toutes les alertes**/
	public boolean disableAllAlertOnAuction(AuctionBean auction);
}
