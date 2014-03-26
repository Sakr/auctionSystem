package fr.auctionSystem.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

/**
 * @author 
 * Cette class nous permet de simuler le changement des jours pour realiser les tests sur le changement
 * de l'etat d'une enchere vers terminee, lorsqu'elle depasse la date limite precisé 
 */
public class Clock extends Observable {

	private Date currentDate;
	
	/**
	 * @param currentDate
	 */
	public Clock(Date currentDate) {
		setCurrentDate(currentDate);
	}

	public static Date addDays(Date date,int days){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,days);
		
		return cal.getTime();
	}

	/**
	 * @return the currentDate
	 */
	public Date getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
		setChanged();
		notifyObservers(this.currentDate);
	}

}
