package fr.auctionSystem.manager;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.ObjectBean;
import fr.auctionSystem.observer.AlertObserver;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Horloge;

public class AuctionManager {

	public AuctionBean getAuctionBean(ObjectBean product, AuctionStateEnum state, Horloge deadLine, Long minimumPrice, Long reservePrice) {
		AuctionBean auction = new AuctionBean(product,state,deadLine,minimumPrice,reservePrice);
		//alerte automatique est ajoutée sur une enchère pour prévenir le vendeur dès qu'une
		//offre est faite sur son enchère
		AlertObserver alertObserver=new AlertObserver();
		auction.addObserver(alertObserver);
		return auction;
	}
	
}
