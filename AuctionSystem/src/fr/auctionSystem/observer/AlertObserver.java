/**
 * 
 */
package fr.auctionSystem.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fr.auctionSystem.bean.AlertBean;
import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.OfferBean;
import fr.auctionSystem.util.Messages;

/**
 * @author slimem
 *
 */
public class AlertObserver  implements Observer {
	/**
	 * Cette classe observe et enregistre un historique des alertes dans une liste
	 */

	private List<AlertBean> listAlert=new ArrayList<AlertBean>(); 
	
	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof AuctionBean){
			Long reservePrice=(Long)obj;
			//reserve price achieved by offering 
		}
		
		if(obs instanceof AuctionBean){
			OfferBean offerBean=(OfferBean)obj;
			listAlert.add(new AlertBean(Messages.OFFER_DONE_ON_AUTION));
			System.out.println("Alert --> "+Messages.OFFER_DONE_ON_AUTION+" par "+offerBean.getUserBean().getFirstName()+" "+offerBean.getUserBean().getSecondName());
		}
	}

	/**
	 * @return the listAlert
	 */
	public List<AlertBean> getListAlert() {
		return listAlert;
	}

	/**
	 * @param listAlert the listAlert to set
	 */
	public void setListAlert(List<AlertBean> listAlert) {
		this.listAlert = listAlert;
	}
	
} 