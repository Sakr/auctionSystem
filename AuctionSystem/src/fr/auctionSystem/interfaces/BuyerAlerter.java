package fr.auctionSystem.interfaces;

import java.util.List;

import fr.auctionSystem.bean.AlertBean;
import fr.auctionSystem.bean.AuctionBean;

public interface BuyerAlerter {
	/**Le prix de reserve a ete atteint par une offre**/
	public boolean addPriceReserveAlert(boolean value,AuctionBean auctionBean);
	/**L'enchere a ete annulee par le vendeur.**/
	public boolean addAuctionCanceledBySellerAlert(boolean value,AuctionBean auctionBean);
	/**Une offre superieure a  celle emise par l'acheteur a ete emise par un autre acheteur.**/
	public boolean addAnotherGreaterOfferAlert(boolean value,AuctionBean auctionBean);
	
	public List<AlertBean> getListLaunchedAlert();
}
