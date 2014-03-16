/**
 * 
 */
package fr.auctionSystem.bean;

import java.util.Date;
import java.util.List;

import fr.auctionSystem.util.AuctionStateEnum;

/**
 * @author Sakr
 *
 */
public class AuctionBean {

	private ObjectBean product;
	private AuctionStateEnum state;
	private Date deadLine;
	private Long minimumPrice;
	private Long reservePrice;
	//L'enchere a plusieurs offres
	private List<OfferBean> listOfferBean;
	
	/**
	 * @param product
	 * @param state
	 * @param deadLine
	 * @param minimumPrice
	 * @param reservePrice
	 * @param listOfferBean
	 */
	public AuctionBean(ObjectBean product, AuctionStateEnum state,
			Date deadLine, Long minimumPrice, Long reservePrice,
			List<OfferBean> listOfferBean) {
		
		this.product = product;
		this.state = state;
		this.deadLine = deadLine;
		this.minimumPrice = minimumPrice;
		this.reservePrice = reservePrice;
		this.listOfferBean = listOfferBean;
	}
	
	/**
	 * @return the product
	 */
	public ObjectBean getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(ObjectBean product) {
		this.product = product;
	}
	/**
	 * @return the state
	 */
	public AuctionStateEnum getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(AuctionStateEnum state) {
		this.state = state;
	}
	/**
	 * @return the deadLine
	 */
	public Date getDeadLine() {
		return deadLine;
	}
	/**
	 * @param deadLine the deadLine to set
	 */
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	/**
	 * @return the minimumPrice
	 */
	public Long getMinimumPrice() {
		return minimumPrice;
	}
	/**
	 * @param minimumPrice the minimumPrice to set
	 */
	public void setMinimumPrice(Long minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	/**
	 * @return the reservePrice
	 */
	public Long getReservePrice() {
		return reservePrice;
	}
	/**
	 * @param reservePrice the reservePrice to set
	 */
	public void setReservePrice(Long reservePrice) {
		this.reservePrice = reservePrice;
	}
	/**
	 * @return the listOfferBean
	 */
	public List<OfferBean> getListOfferBean() {
		return listOfferBean;
	}
	/**
	 * @param listOfferBean the listOfferBean to set
	 */
	public void setListOfferBean(List<OfferBean> listOfferBean) {
		this.listOfferBean = listOfferBean;
	}
	
	
	
	
}
