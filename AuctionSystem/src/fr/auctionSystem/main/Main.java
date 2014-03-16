/**
 * 
 */
package fr.auctionSystem.main;


import fr.auctionSystem.alertes.Alert;
import fr.auctionSystem.alertes.AlertBuyer;
import fr.auctionSystem.alertes.AlertImpl;
import fr.auctionSystem.alertes.AlertLauncher;
import fr.auctionSystem.alertes.AlertManager;

/**
 * @author Sakr
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Alert alert=new AlertImpl();
		
		AlertLauncher alertLauncher=new AlertBuyer();
		((AlertImpl)alert).setAlertLauncher(alertLauncher);
		AlertManager alertManager=new AlertManager();
		alertManager.setAlertMessageByAlertLauncher(alertLauncher, alert);
		
	}

}
