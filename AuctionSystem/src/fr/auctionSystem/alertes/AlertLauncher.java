/**
 * 
 */
package fr.auctionSystem.alertes;

import fr.auctionSystem.bean.AlertBean;

/**
 * @author Sakr
 *
 */
public interface AlertLauncher {
	public void communicationWithUser(AlertBean alertBean);
}
