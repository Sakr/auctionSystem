package fr.auctionSystem.classes;

import java.util.Date;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.ObjectBean;
import fr.auctionSystem.bean.OfferBean;
import fr.auctionSystem.bean.UserBean;
import fr.auctionSystem.comparator.ObjectComparator;
import fr.auctionSystem.interfaces.BuyerRole;
import fr.auctionSystem.interfaces.SellerRole;
import fr.auctionSystem.observer.AlertObserver;
import fr.auctionSystem.util.AlertType;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Clock;
import fr.auctionSystem.util.Messages;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author 
 *
 */
public class User extends UserBean implements SellerRole, BuyerRole {

	public User(String login, String firstName, String secondName, RoleEnum role) {
		super(login, firstName, secondName, role);
	}

	@Override
	public boolean issueOffer(AuctionBean auction, Long price) {
		
		//Si l'encher n'appartient pas a l'utilisateur
		if(this.getListAuctionBean().get(auction.getAuctionId())==null){
			//Si l'enchere est publiee
			if(auction.getState().equals(AuctionStateEnum.PUBLISHED)){
				//On creer l'offre
				OfferBean offerBean=null;
				//Il n'est pas possible d'emmettre une offre qu'au dessous du prix minimum
				if(price>auction.getMinimumPrice()){
					offerBean=new OfferBean(price,this);
					auction.addOfferBean(offerBean);
					
					//Lorsque une offre est emise sur l'enchere, on met a jour le prix minimum de l'enchere
					auction.setMinimumPrice(offerBean.getPrice());
					return true;
				}else{
					System.out.println(Messages.NO_RIGHT_ISSUE_OFFER_BELOW_MINIMUM_PRICE);
					return false;
				}
			}else{
				System.out.println(Messages.NO_RIGHT_ISSUE_OFFER_NOT_PUBLISHED);
				return false;
			}
		}else{//sinon il n'a pas le droit d'emmetre des offres sur son offre
			System.out.println(Messages.NO_RIGHT_ISSUE_OFFER);
			return false;
		}
	}

	@Override
	public AuctionBean createAuction(AuctionStateEnum state, Date deadLine, Clock creationClock, Long minimumPrice, Long reservePrice, ObjectBean product) {
		AuctionBean auction;
		if(this.getRole().equals(RoleEnum.SELLER_BUYER) || this.getRole().equals(RoleEnum.SELLER)){
			//On cree l'enchere 
			auction = new AuctionBean(product,state,deadLine,creationClock,minimumPrice,reservePrice);
			//alerte automatique est ajoutee sur une enchere pour prevenir le vendeur des qu'une
			//offre est faite sur son enchere
			AlertObserver automaticAlertObserver=new AlertObserver(new Object(),this,AlertType.ALERT_AUTOMATIC);
			auction.addObserver(automaticAlertObserver);
			//On lui ajoute un id
			auction.setAuctionId();
			//On ajoute l'enchere sur la liste des encheres avec son id en cle de l'Hashmap
			this.getListAuctionBean().put(auction.getAuctionId(),auction);
			System.out.println(Messages.AUCTION_CREATED);
		}else{
			System.out.println(Messages.NO_RIGHT_CREATE_AUCTION);
			auction=null;
		}
		
		
		return auction;
	}

	@Override
	public boolean postAuction(AuctionBean auction) {
		
		if(this.getRole().equals(RoleEnum.SELLER_BUYER) || this.getRole().equals(RoleEnum.SELLER)){
			//On verifie si l'enchere appartient a l'utilisateur
			if(this.getListAuctionBean().get(auction.getAuctionId())!=null){
				if(!auction.getState().equals(AuctionStateEnum.PUBLISHED)){
					auction.setState(AuctionStateEnum.PUBLISHED);
					System.out.println(Messages.AUCTION_PUBLISHED);
					return true;
				}else{
					System.out.println(Messages.AUCTION_ALREADY_PUBLISHED);
					return false;
				}
			}else{
				System.out.println(Messages.AUCTION_NOT_BELONG_TO_USER);
				return false;
			}
		}else{
			System.out.println(Messages.NO_RIGHT_POST_AUCTION);
			return false;
		}
		
		
	}
	
	private boolean doesAnOfferReachedReservePrice(AuctionBean auction){
		boolean response=false;
		for(OfferBean offer:auction.getListOfferBean()){
			if(this.getListAuctionBean().get(auction.getAuctionId()).getReservePrice()<=offer.getPrice()){
				response=true;
			}
		}
		
		return response;
		
	}
	
	@Override
	public boolean cancelAuction(AuctionBean auction) {
		
		if(this.getRole().equals(RoleEnum.SELLER_BUYER) || this.getRole().equals(RoleEnum.SELLER)){
			//On verifie si l'enchere appartient a l'utilisateur
			if(this.getListAuctionBean().get(auction.getAuctionId())!=null){
				
				//On verifie si elle n'est pas deja annulee
				if(!auction.getState().equals(AuctionStateEnum.CANCELED)){
					//Et on verifie si aucune offre sur cette enchere n'a atteint le prix de reserve de l'enchere.
					if(!doesAnOfferReachedReservePrice(auction)){
						auction.setState(AuctionStateEnum.CANCELED);
						return true;
					}else{
						System.out.println(Messages.NO_RIGHT_CANCEL_AUCTION_RESERVE_PRICE);
						return false;
					}
				}else{
					System.out.println(Messages.AUCTION_ALREADY_CANCELED);
					return false;
				}
			}else{
				System.out.println(Messages.AUCTION_NOT_BELONG_TO_USER);
				return false;
			}
		}else{
			System.out.println(Messages.NO_RIGHT_CANCEL_AUCTION);
			return false;
		}
		
	}
	@Override
	public boolean specifyMinimumPriceForAuction(AuctionBean auction, Long minimumPrice) {
		
		if(this.getRole().equals(RoleEnum.SELLER_BUYER) || this.getRole().equals(RoleEnum.SELLER)){
			//On verifie si l'enchere appartient a l'utilisateur
			if(this.getListAuctionBean().get(auction.getAuctionId())!=null){
				if(!AuctionStateEnum.PUBLISHED.equals(auction.getState())){
					auction.setMinimumPrice(minimumPrice);
					return true;
				}else{
					System.out.println(Messages.AUCTION_IS_PUBLISHED);
					return false;
				}
				
			}else{
				System.out.println(Messages.AUCTION_NOT_BELONG_TO_USER);
				return false;
			}
		}else{
			System.out.println(Messages.NO_SLLER_ROLE);
			return false;
		}
		
	}

	@Override
	public boolean specifyReservePriceForAuction(AuctionBean auction, Long reservePrice) {
		
		if(this.getRole().equals(RoleEnum.SELLER_BUYER) || this.getRole().equals(RoleEnum.SELLER)){
			//On verifie si l'enchere appartient a l'utilisateur
			if(this.getListAuctionBean().get(auction.getAuctionId())!=null){
				
				if(!AuctionStateEnum.PUBLISHED.equals(auction.getState())){
					auction.setReservePrice(reservePrice);
					return true;
				}else{
					System.out.println(Messages.AUCTION_IS_PUBLISHED);
					return false;
				}
			}else{
				System.out.println(Messages.AUCTION_NOT_BELONG_TO_USER);
				return false;
			}
		}else{
			System.out.println(Messages.NO_SLLER_ROLE);
			return false;
		}
	}
	
	@Override
	public Long getReservePriceForAuction(AuctionBean auction) {
		if(this.getRole().equals(RoleEnum.SELLER_BUYER) || this.getRole().equals(RoleEnum.SELLER)){
			//On verifie si l'enchere appartient a l'utilisateur
			if(this.getListAuctionBean().get(auction.getAuctionId())!=null){
				
				if(AuctionStateEnum.PUBLISHED.equals(auction.getState())){
					return auction.getReservePrice();
				}else{
					System.out.println(Messages.AUCTION_IS_PUBLISHED);
					return null;
				}
			}else{
				System.out.println(Messages.AUCTION_NOT_BELONG_TO_USER);
				return null;
			}
		}else{
			System.out.println(Messages.NO_SLLER_ROLE);
			return null;
		}
	}
	
	@Override
	public Long getMinimumPriceOfAuction(AuctionBean auction) {
		return auction.getMinimumPrice();
	}
	
	@Override
	public boolean addPriceReserveAlert(boolean value,AuctionBean auctionBean) {
		ObjectComparator objectComparator=new ObjectComparator();
		
		//Si l'utilisateur est acheteur, ou un acheteur vendeur
		if(this.getRole().equals(RoleEnum.BUYER)|| this.getRole().equals(RoleEnum.SELLER_BUYER)){
			
			//il faut que l'enchere ne sois pas a l'utilisateur
			if(this.getListAuctionBean().get(auctionBean.getAuctionId())==null){
				if(value){
					AlertObserver userAlertObserver=new AlertObserver(new Object(),this,AlertType.ALERT_PRICE_RESERVE);
					auctionBean.addAlertObserver(userAlertObserver);
					System.out.println(Messages.ALERT_ADD);
					return true;
				}else{
					for (AlertObserver alert : auctionBean.getListObserverAlert()) {
						if (objectComparator.compare(alert.getAlertUser(), this) == 0 && alert.getAlertType().equals(AlertType.ALERT_PRICE_RESERVE)) {
							auctionBean.deleteObserver(alert);
						}
					}

					System.out.println(Messages.ALERT_REMOVE);
					return true;
				}
			}else{
				System.out.println(Messages.AUCTION_BELONG_TO_USER);
				return false;
			}
		}else{
			System.out.println(Messages.NO_RIGHT_ADD_ALERT_AUCTION);
			return false;
		}
	}

	@Override
	public boolean addAuctionCanceledBySellerAlert(boolean value,AuctionBean auctionBean) {
		ObjectComparator objectComparator=new ObjectComparator();
		
		//Si l'utilisateur est acheteur, ou un acheteur vendeur
		if(this.getRole().equals(RoleEnum.BUYER)|| this.getRole().equals(RoleEnum.SELLER_BUYER)){
			//il faut que l'enchere ne sois pas a l'utilisateur
			if(this.getListAuctionBean().get(auctionBean.getAuctionId())==null){
				if(value){
					AlertObserver userAlertObserver=new AlertObserver(new Object(),this,AlertType.ALERT_CANCELED_AUCTION);
					auctionBean.addAlertObserver(userAlertObserver);
					System.out.println(Messages.ALERT_ADD);
					return true;
				}else{
					for (AlertObserver alert : auctionBean.getListObserverAlert()) {
						if (objectComparator.compare(alert.getAlertUser(), this) == 0 && alert.getAlertType().equals(AlertType.ALERT_CANCELED_AUCTION)) {
							auctionBean.deleteObserver(alert);
						}
					}
					
					System.out.println(Messages.ALERT_REMOVE);
					return true;
				}
			}else{
				System.out.println(Messages.AUCTION_BELONG_TO_USER);
				return false;
			}	
		}else{
			System.out.println(Messages.NO_RIGHT_ADD_ALERT_AUCTION);
			return false;
		}
	}

	@Override
	public boolean addAnotherGreaterOfferAlert(boolean value, AuctionBean auctionBean) {
		ObjectComparator objectComparator=new ObjectComparator();
		
		if(this.getRole().equals(RoleEnum.BUYER)|| this.getRole().equals(RoleEnum.SELLER_BUYER)){
			//il faut que l'enchere ne sois pas a l'utilisateur
			if(this.getListAuctionBean().get(auctionBean.getAuctionId())==null){
				if(value){
					AlertObserver userAlertObserver=new AlertObserver(new Object(),this,AlertType.ALERT_GREATER_OFFER);
					auctionBean.addAlertObserver(userAlertObserver);
					System.out.println(Messages.ALERT_ADD);
					return true;
				}else{
					for (AlertObserver alert : auctionBean.getListObserverAlert()) {
						if (objectComparator.compare(alert.getAlertUser(), this) == 0 && alert.getAlertType().equals(AlertType.ALERT_GREATER_OFFER)) {
							auctionBean.deleteObserver(alert);
						}
					}
					
					System.out.println(Messages.ALERT_REMOVE);
					return true;
				}
			}else{
				System.out.println(Messages.AUCTION_BELONG_TO_USER);
				return false;
			}	
		}else{
			System.out.println(Messages.NO_RIGHT_ADD_ALERT_AUCTION);
			return false;
		}
	}
	
	@Override
	public boolean disableAllAlertOnAuction(AuctionBean auction) {
		ObjectComparator objectComparator=new ObjectComparator();
		
		if (!auction.getListObserverAlert().isEmpty()) {
			//Supprime tous les observers de cet utilisateur sur cette enchere 
			for (AlertObserver alert : auction.getListObserverAlert()) {
				if (objectComparator.compare(alert.getAlertUser(), this) == 0) {
					auction.deleteObserver(alert);	
				}
			}
			System.out.println(Messages.ALL_ALERT_REMOVE + auction.getProduct().getIdentifier());
			return true;
		}
		
		System.out.println(Messages.ALL_ALERT_NOT_REMOVE + this.getLogin());
		return false;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
