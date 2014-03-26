package fr.auctionSystem.bean;

/**
 * @author 
 *
 */
public class ObjectBean {
	private String identifier;
	private String description;
	
	/**
	 * @param identifier
	 * @param description
	 */
	public ObjectBean(String identifier, String description) {
		this.identifier = identifier;
		this.description = description;
	}
	
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
