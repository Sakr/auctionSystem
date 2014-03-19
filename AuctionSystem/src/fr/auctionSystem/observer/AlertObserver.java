/**
 * 
 */
package fr.auctionSystem.observer;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fr.auctionSystem.bean.AlertBean;
import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.util.Messages;

/**
 * @author slimem
 *
 */
public class AlertObserver extends ArrayList<AlertBean> implements Observer {
	/**
	 * Cette classe observe et enregistre un historique des alertes dans une liste
	 */

	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof AuctionBean){
			Long reservePrice=(Long)obj;
			//Teste bidon
			if(reservePrice>5){
				this.add(new AlertBean(Messages.AUCTION_RESERVE_PRICE_REACHED_MESSAGE));
				System.out.println("Alert --> "+Messages.AUCTION_RESERVE_PRICE_REACHED_MESSAGE+" --> "+reservePrice);
			}
		}
	}
} 