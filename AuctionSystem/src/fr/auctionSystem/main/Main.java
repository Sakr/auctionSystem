/**
 * 
 */
package fr.auctionSystem.main;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.auctionSystem.alertes.Alert;
import fr.auctionSystem.alertes.AlertBuyer;
import fr.auctionSystem.alertes.AlertImpl;
import fr.auctionSystem.alertes.AlertLauncher;
import fr.auctionSystem.alertes.AlertManager;
import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.ObjectBean;
import fr.auctionSystem.bean.OfferBean;
import fr.auctionSystem.observer.AlertObserver;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Horloge;

/**
 * @author Sakr
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectBean product=new ObjectBean("Teste","Teste");
		Horloge horloge=new Horloge();
		List<OfferBean> listOfferBean =new ArrayList<OfferBean>();
		AuctionBean auction = new AuctionBean(product, AuctionStateEnum.CREATED, horloge, new Long(200), new Long(200), listOfferBean);
		AlertObserver alertObserver=new AlertObserver();
		auction.addObserver(alertObserver);
		//Teste Bidon: Lorsque le prix de reserve est superieur ou egale a 5 il notifie
		auction.setReservePrice(new Long("2"));
	    auction.setReservePrice(new Long("3"));
	    auction.setReservePrice(new Long("4"));
	    auction.setReservePrice(new Long("5"));
	    auction.setReservePrice(new Long("6"));
	    auction.setReservePrice(new Long("7"));
	    auction.setReservePrice(new Long("8"));
	    auction.setReservePrice(new Long("9"));
	    
	}

}
