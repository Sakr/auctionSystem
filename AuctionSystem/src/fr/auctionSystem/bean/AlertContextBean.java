package fr.auctionSystem.bean;

import fr.auctionSystem.classes.User;
import fr.auctionSystem.util.AlertType;


/**
 * @author 
 * L'orsqu'une alerte est lancé, on construit ce bean avec les données qu'on veux transmettre a l'observeur
 */
public class AlertContextBean {
	
	
	private Object alertObject;
	private User alertUser;
	private AlertType alertType;

	/**
	 * @param alertObject
	 * @param alertUser
	 * @param alertClock
	 * @param alertType
	 */
	public AlertContextBean(Object alertObject, User alertUser,
			 AlertType alertType) {
		this.alertObject = alertObject;
		this.alertUser = alertUser;
		this.alertType = alertType;
	}
	/**
	 * @return the alertType
	 */
	public AlertType getAlertType() {
		return alertType;
	}
	/**
	 * @param alertType the alertType to set
	 */
	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}
	/**
	 * @return the alertObject
	 */
	public Object getAlertObject() {
		return alertObject;
	}
	/**
	 * @param alertObject the alertObject to set
	 */
	public void setAlertObject(Object alertObject) {
		this.alertObject = alertObject;
	}
	/**
	 * @return the alertUser
	 */
	public User getAlertUser() {
		return alertUser;
	}
	/**
	 * @param alertUser the alertUser to set
	 */
	public void setAlertUser(User alertUser) {
		this.alertUser = alertUser;
	}
	
}
