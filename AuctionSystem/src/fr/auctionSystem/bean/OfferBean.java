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
	private UserBean userBean;
	
	/**
	 * @param buyer
	 * @param price
	 */
	public OfferBean(Long price,UserBean userBean) {

		this.price = price;
		this.userBean = userBean;
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

	/**
	 * @return the userBean
	 */
	public UserBean getUserBean() {
		return userBean;
	}

	/**
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	
	
}
