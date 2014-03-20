package fr.auctionSystem.manager;

import java.util.ArrayList;
import java.util.List;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.ObjectBean;
import fr.auctionSystem.bean.OfferBean;
import fr.auctionSystem.observer.AlertObserver;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Horloge;

public class AuctionManager {

	public AuctionBean getAuctionBean() {
		
		ObjectBean product=new ObjectBean();
		Horloge horloge=new Horloge();
		List<OfferBean> listOfferBean =new ArrayList<OfferBean>();
		AuctionBean auction = new AuctionBean(product, AuctionStateEnum.CREATED, horloge, new Long(200), new Long(200), listOfferBean);
		AlertObserver alertObserver=new AlertObserver();
		auction.addObserver(alertObserver);
		return auction;
	}
	
}
