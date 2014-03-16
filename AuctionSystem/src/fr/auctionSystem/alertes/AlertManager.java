/**
 * 
 */
package fr.auctionSystem.alertes;

import fr.auctionSystem.bean.AlertBean;
import fr.auctionSystem.util.Messages;

/**
 * @author Sakr
 *
 */
public class AlertManager {
	
	public void setAlertMessageByAlertLauncher(AlertLauncher alertLauncher,Alert alert){
		if(alertLauncher instanceof AlertSeller){
			
			AlertBean alertCanceledBean=new AlertBean(Messages.AUCTION_CANCELED_MESSAGE);
			alert.disableAlert(alertCanceledBean);
			
		}else if(alertLauncher instanceof AlertBuyer){
			
			int id=0;
			switch (id) {
			case 1:
				AlertBean alertPriceChangedBean=new AlertBean(Messages.AUCTION_PRICE_CHANGED_MESSAGE);
				alert.launchAlert(alertPriceChangedBean);
				break;
			default:
				AlertBean alertReservePriceBean=new AlertBean(Messages.AUCTION_RESERVE_PRICE_REACHED_MESSAGE);
				alert.disableAlert(alertReservePriceBean);
				break;
			}
		}
		
	}
}
