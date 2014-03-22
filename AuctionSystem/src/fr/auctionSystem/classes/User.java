/**
 * 
 */
package fr.auctionSystem.classes;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.auctionSystem.bean.AlertBean;
import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.ObjectBean;
import fr.auctionSystem.bean.OfferBean;
import fr.auctionSystem.bean.UserBean;
import fr.auctionSystem.interfaces.SellerRole;
import fr.auctionSystem.observer.AlertObserver;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Clock;
import fr.auctionSystem.util.Messages;
import fr.auctionSystem.util.RoleEnum;
/**
 * @author slimem
 *
 */
public class User extends UserBean implements SellerRole{

	private Map<Integer,AuctionBean> listAuctionBean=new HashMap<Integer,AuctionBean>();
	private List<AlertBean> listAlertBean=new ArrayList<AlertBean>(); 
	private AlertObserver alertObserver;
	
	public User(String login, String firstName, String secondName, RoleEnum role) {
		super(login, firstName, secondName, role);
	}

	@Override
	public boolean issueOffer(AuctionBean auction, Long price) {
		
		//Si l'encher n'appartient pas a l'utilisateur
		if(listAuctionBean.get(auction.getAuctionId())==null){
			
			//Si l'enchere est publiee
			if(auction.getState().equals(AuctionStateEnum.PUBLISHED)){
				
				//On creer l'offre
				OfferBean offerBean=null;
				//Il n'est pas possible d'emmettre une offre au dessous du prix minimum
				if(price>auction.getMinimumPrice()){
					offerBean=new OfferBean(price,this);
					auction.addOfferBean(offerBean);
					return true;
				}else{
					System.out.println(Messages.NO_RIGHT_ISSUE_OFFER_BELOW_MINIMUM_PRICE);
					return false;
				}
				
			}else{
				System.out.println(Messages.NO_RIGHT_ISSUE_OFFER_NOT_PUBLISHED);
				return false;
			}
		}else{//sinon il n'a pas le droit d'emmetre des offres dessus
			System.out.println(Messages.NO_RIGHT_ISSUE_OFFER);
			return false;
		}
	}

	@Override
	public AuctionBean createAuction(AuctionStateEnum state, Clock deadLine, Long minimumPrice, Long reservePrice) {
		AuctionBean auction;
		if(this.getRole().equals(RoleEnum.SELLER_BUYER) || this.getRole().equals(RoleEnum.SELLER)){
			//On crée l'enchere 
			ObjectBean product=new ObjectBean();
			auction = new AuctionBean(product,state,deadLine,minimumPrice,reservePrice);
			//alerte automatique est ajoutée sur une enchère pour prévenir le vendeur dès qu'une
			//offre est faite sur son enchère
			alertObserver=new AlertObserver();
			auction.addObserver(alertObserver);
			//On lui ajoute un id
			auction.setAuctionId();
			//On ajoute l'enchere sur la liste des encheres avec son id en clé de l'Hashmap
			listAuctionBean.put(auction.getAuctionId(),auction);
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
			if(listAuctionBean.get(auction.getAuctionId())!=null){
				if(!auction.getState().equals(AuctionStateEnum.PUBLISHED)){
					auction.setState(AuctionStateEnum.PUBLISHED);
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

	@Override
	public boolean cancelAuction(AuctionBean auction) {
		
		if(this.getRole().equals(RoleEnum.SELLER_BUYER) || this.getRole().equals(RoleEnum.SELLER)){
			//On verifie si l'enchere appartient a l'utilisateur
			if(listAuctionBean.get(auction.getAuctionId())!=null){
				if(!auction.getState().equals(AuctionStateEnum.CANCELED)){
					auction.setState(AuctionStateEnum.CANCELED);
					return true;
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
	
	

	/**
	 * @return the listAuctionBean
	 */
	public Map<Integer, AuctionBean> getListAuctionBean() {
		return listAuctionBean;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean addPriceReserveAlert(boolean value,AuctionBean auctionBean) {
		if(this.getRole().equals(RoleEnum.BUYER)|| this.getRole().equals(RoleEnum.SELLER_BUYER)){
			this.addObserver(alertObserver);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean addAuctionCanceledBySellerAlert(boolean value,AuctionBean auctionBean) {
		if(this.getRole().equals(RoleEnum.BUYER)|| this.getRole().equals(RoleEnum.SELLER_BUYER)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean addAnotherGreaterOfferAlert(boolean value,AuctionBean auctionBean) {
		if(this.getRole().equals(RoleEnum.BUYER)|| this.getRole().equals(RoleEnum.SELLER_BUYER)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<AlertBean> getListLaunchedAlert() {
		for(AlertBean alertBean: alertObserver.getListAlert()){
			listAlertBean.add(alertBean);
		}
		return listAlertBean;
	}

}
