/**
 * 
 */
package fr.auctionSystem.bean;

/**
 * @author 
 *
 */
public class AlertBean {
	
	private String message;
	
	/**
	 * @param message
	 */
	public AlertBean(String message) {
		super();
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
