/**
 * 
 */
package fr.auctionSystem.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fr.auctionSystem.observer.AlertObserver;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Clock;

/**
 * @author david
 *
 */
public class AuctionBean extends Observable implements Observer {

	private ObjectBean product;
	private AuctionStateEnum state;
	private Date deadLine;
	private Clock creationClock;
	private Long minimumPrice;
	private Long reservePrice;
	//L'enchere a plusieurs offres
	private List<OfferBean> listOfferBean=new ArrayList<OfferBean>();
	
	//L'enchere peut avoir plusieurs alertes (observer)
	private List<AlertObserver> listObserverAlert=new ArrayList<AlertObserver>();
	
	//Cette Id est id technique (un attribut de classe) qui est incremente a chaque fois qu'on instancie un Auctionbean
	//Cette Id est setter dans l'id du bean lorsqu'on appel setAuctionId()
	private static int technicalAuctionId=0;
	
	//cette id est l'id qui servira a identifier l'objet instancie (un attribut d'objet)
	private int auctionId;
	
	
	/**
	 * @param product
	 * @param state
	 * @param deadLine
	 * @param creationClock
	 * @param minimumPrice
	 * @param reservePrice
	 * @param listOfferBean
	 * @param auctionId
	 */
	
	public AuctionBean(ObjectBean product, AuctionStateEnum state,
			Date deadLine,Clock creationClock, Long minimumPrice, Long reservePrice) {
		this.product = product;
		this.state = state;
		this.deadLine = deadLine;
		this.creationClock=creationClock;
		this.minimumPrice = minimumPrice;
		this.reservePrice = reservePrice;
		this.creationClock.addObserver(this);
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
		this.setChanged();
		this.notifyObservers(this.state);
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
		this.setChanged();
		this.notifyObservers(this.deadLine);
	}
	
	/**
	 * @return the creationClock
	 */
	public Clock getCreationClock() {
		return creationClock;
	}
	/**
	 * @param creationClock the creationClock to set
	 */
	public void setCreationClock(Clock creationClock) {
		this.creationClock = creationClock;
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
		this.setChanged();
		this.notifyObservers(this.reservePrice);
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
		//Alerte automatique des qu'une offre est ajoute
		this.listOfferBean.add(offerBean);
		this.setChanged();
		this.notifyObservers(offerBean);
	}
	@Override
	public String toString() {
		return "AuctionBean [product=" + product + ", state=" + state
				+ ", deadLine=" + deadLine + ", minimumPrice=" + minimumPrice
				+ ", reservePrice=" + reservePrice + ", listOfferBean="
				+ listOfferBean + ", auctionId=" + auctionId + "]";
	}
	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof Clock){
			if(this.deadLine.compareTo((Date)obj)<=0){
				setState(AuctionStateEnum.COMPLETED);
			}
		}
		
	}
	
	/**
	 * @return the listObserverAlert
	 */
	public List<AlertObserver> getListObserverAlert() {
		return listObserverAlert;
	}

	public void addAlertObserver(AlertObserver userAlertObserver) {
		this.addObserver(userAlertObserver);
		listObserverAlert.add(userAlertObserver);
		
	}

	public void deleteAlertObserver(AlertObserver userAlertObserver) {
		this.deleteObserver(userAlertObserver);
		listObserverAlert.remove(userAlertObserver);
		
	}


}
