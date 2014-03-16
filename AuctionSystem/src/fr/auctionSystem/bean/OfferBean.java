/**
 * 
 */
package fr.auctionSystem.bean;

/**
 * @author Sakr
 *
 */
public class OfferBean {
	

	private Long price;
	
	/**
	 * @param buyer
	 * @param price
	 */
	public OfferBean(Long price) {

		this.price = price;
	}

	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}
	
	
	
}
