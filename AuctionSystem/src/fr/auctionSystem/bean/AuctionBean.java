/**
 * 
 */
package fr.auctionSystem.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Clock;
import fr.auctionSystem.util.Messages;

/**
 * @author Sakr
 *
 */
public class AuctionBean extends Observable{

	private ObjectBean product;
	private AuctionStateEnum state;
	private Clock deadLine;
	private Long minimumPrice;
	private Long reservePrice;
	//L'enchere a plusieurs offres
	private List<OfferBean> listOfferBean=new ArrayList<OfferBean>();
	
	//Cette Id est id technique (un attribut de classe) qui est incrémenté a chaque fois qu'on instancie un Auctionbean
	//Cette Id est setter dans l'id du bean lorsqu'on appel setAuctionId()
	private static int technicalAuctionId=0;
	
	//cette id est l'id qui servira a identifier l'objet instancié (un attribut d'objet)
	private int auctionId;
	
	
	/**
	 * @param product
	 * @param state
	 * @param deadLine
	 * @param minimumPrice
	 * @param reservePrice
	 * @param listOfferBean
	 * @param listener
	 */
	public AuctionBean(ObjectBean product, AuctionStateEnum state,
			Clock deadLine, Long minimumPrice, Long reservePrice) {
		this.product = product;
		this.state = state;
		this.deadLine = deadLine;
		this.minimumPrice = minimumPrice;
		this.reservePrice = reservePrice;
		AuctionBean.technicalAuctionId ++;
	}
	public AuctionBean() {
		
	}
	/**
	 * @return the auctionId
	 */
	public int getAuctionId() {
		return auctionId;
	}

	/**
	 * @param auctionId the auctionId to set
	 * @return 
	 */
	public void setAuctionId() {
		this.auctionId=AuctionBean.technicalAuctionId;
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
	public Clock getDeadLine() {
		return deadLine;
	}
	/**
	 * @param deadLine the deadLine to set
	 */
	public void setDeadLine(Clock deadLine) {
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
		if(reservePrice==-1L){
			System.out.println(Messages.NOT_YOUR_AUCTION);
		}
		return reservePrice;
	}
	/**
	 * @param reservePrice the reservePrice to set
	 */
	public void setReservePrice(Long reservePrice) {
		this.reservePrice = reservePrice;
		setChanged();
		notifyObservers(this.reservePrice);
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
	public void addOfferBean(OfferBean offerBean) {
		//Alerte automatique des qu'une offre est ajoutée
		this.listOfferBean.add(offerBean);
		setChanged();
		notifyObservers(offerBean);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuctionBean [product=" + product + ", state=" + state
				+ ", deadLine=" + deadLine + ", minimumPrice=" + minimumPrice
				+ ", reservePrice=" + reservePrice + ", listOfferBean="
				+ listOfferBean + ", auctionId=" + auctionId + "]";
	}

	
}

