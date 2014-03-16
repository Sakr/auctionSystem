/**
 * 
 */
package fr.auctionSystem.alertes;

import fr.auctionSystem.bean.AlertBean;

/**
 * @author Sakr
 *
 */
public interface Alert {
	void launchAlert(AlertBean alertBean);
	void disableAlert(AlertBean alertBean);

}
