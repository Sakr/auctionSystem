/**
 * 
 */
package fr.auctionSystem.observer;

import java.util.Observable;
import java.util.Observer;

import fr.auctionSystem.bean.AlertBean;
import fr.auctionSystem.bean.AlertContextBean;
import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.OfferBean;
import fr.auctionSystem.classes.User;
import fr.auctionSystem.comparator.ObjectComparator;
import fr.auctionSystem.util.AlertType;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Messages;

/**
 * @author slimem
 */
public class AlertObserver extends AlertContextBean implements Observer {
	
	public AlertObserver(Object alertObject, User alertUser,
			AlertType alertType) {
		super(alertObject, alertUser, alertType);
	}

	@Override
	public void update(Observable obs, Object obj) {
		
		if(obs instanceof AuctionBean){
			
			AuctionBean auctionBean=(AuctionBean)obs;
			AlertBean alertBean =getAlertBean(obj, auctionBean);
			if(alertBean!=null){
				System.out.println(alertBean.getMessage());
			}
		}
	}
	
	/**
	 * @param obj
	 * @param auctionBean
	 * @return AlertBean 
	 */
	private AlertBean getAlertBean(Object obj, AuctionBean auctionBean) {
		
		AlertBean alert=null;
		if(obj instanceof OfferBean){
			
			if (AlertType.ALERT_AUTOMATIC.equals(this.getAlertType())){
				OfferBean offerBean=(OfferBean)obj;
				alert=new AlertBean(Messages.ALERT_TO + this.getAlertUser() + Messages.OFFER_DONE_ON_AUCTION + auctionBean.getProduct().getIdentifier() + Messages.OFFER_DONE_ON_AUCTION_PRODUCT + offerBean.getPrice() + Messages.DEVISE);
			}
			else if(AlertType.ALERT_PRICE_RESERVE.equals(this.getAlertType())){
				
				for(OfferBean offerBean:auctionBean.getListOfferBean()){
					if(offerBean.getPrice()>=auctionBean.getReservePrice()){
						System.out.println();
						alert=new AlertBean(Messages.ALERT_TO + this.getAlertUser() + Messages.RESERVE_PRICE_REACHED_BY_OFFER);
						break;
					}
				}
				
			}else if(AlertType.ALERT_GREATER_OFFER.equals(this.getAlertType())){
				OfferBean currentOfferFromAuction=(OfferBean)obj;
				for(OfferBean offerBean:auctionBean.getListOfferBean()){
					//L'offre que l'utilisateur a sur cette enchere 
					ObjectComparator objectComparator=new ObjectComparator();
					if(objectComparator.compare(offerBean.getUserBean(), this.getAlertUser())==0){
						if(currentOfferFromAuction.getPrice()>=offerBean.getPrice()){
							alert=new AlertBean(Messages.ALERT_TO + this.getAlertUser() + Messages.AUCTION_PRICE_CHANGED_MESSAGE + auctionBean.getProduct().getIdentifier());
							break;
						}
					}
				}
			}
		}else if(obj instanceof AuctionStateEnum){
			if(AlertType.ALERT_CANCELED_AUCTION.equals(this.getAlertType())   && !AlertType.ALERT_AUTOMATIC.equals(this.getAlertType())){
				
				AuctionStateEnum state=(AuctionStateEnum)obj;
				if(state.equals(AuctionStateEnum.CANCELED)){
					
					alert=new AlertBean(Messages.ALERT_TO + this.getAlertUser() + Messages.AUCTION_CANCELED_MESSAGE + auctionBean.getProduct().getIdentifier());
				
				}else if(state.equals(AuctionStateEnum.COMPLETED)){
					//On cherche si l'offre a ete emportee par un utilisateur
					for(OfferBean offer:auctionBean.getListOfferBean()){
						if(offer.getPrice()>auctionBean.getReservePrice()){
							System.out.println("L'enchere "+auctionBean.toString()+" est terminee, elle a ete emportee par"+offer.getUserBean().toString());
							break;
						}
					}
				}
			}
		}
		//On ajoute l'alert sur la liste des alertes de l'utilisateur
		this.getAlertUser().getListAlertBean().add(alert);
		return alert;
	}


} 