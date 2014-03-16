/**
 * 
 */
package fr.auctionSystem.alertes;

import fr.auctionSystem.bean.AlertBean;

/**
 * @author Sakr
 *
 */
public class AlertBuyer implements AlertLauncher{
	@Override
	public void communicationWithUser(AlertBean alertBean) {
		System.out.println(alertBean.getMessage());
	}
}
