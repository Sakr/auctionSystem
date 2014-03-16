/**
 * 
 */
package fr.auctionSystem.alertes;

import fr.auctionSystem.bean.AlertBean;

/**
 * @author Sakr
 *
 */
public class AlertImpl implements Alert{

	private AlertLauncher alertLauncher;
	
	@Override
	public void launchAlert(AlertBean alertBean) {
		System.out.println("Launching alert...");
		alertLauncher.communicationWithUser(alertBean); 
	}

	@Override
	public void disableAlert(AlertBean alertBean) {
		System.out.println("Disabling alert...");
		alertLauncher.communicationWithUser(alertBean);
	}

	/**
	 * @return the alertLauncher
	 */
	public AlertLauncher getAlertLauncher() {
		return alertLauncher;
	}

	/**
	 * @param alertLauncher the alertLauncher to set
	 */
	public void setAlertLauncher(AlertLauncher alertLauncher) {
		this.alertLauncher = alertLauncher;
	}
	
}
