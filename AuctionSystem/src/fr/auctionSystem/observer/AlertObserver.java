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
 * @author david
 *
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
						alert=new AlertBean(Messages.ALERT_TO + this.getAlertUser() + Messages.RESERVE_PRICE_REACHED_BY_OFFER + auctionBean.getProduct().getIdentifier());
						break;
					}
				}
				
			}else if(AlertType.ALERT_GREATER_OFFER.equals(this.getAlertType())){
				OfferBean currentOfferFromAuction=(OfferBean)obj;
				//On parcours la liste des offres sur cette enchere pour connaitre la meilleure offre du user a notifie
				for(OfferBean offerBean:auctionBean.getListOfferBean()){
					ObjectComparator objectComparator=new ObjectComparator();
					//On alerte le user uniquement si l'offre courante ne lui appartient pas et si l'offre courante est superieur a l'une des offres su user a alerter
					if(objectComparator.compare(offerBean.getUserBean(), this.getAlertUser())==0){
						if(objectComparator.compare(currentOfferFromAuction.getUserBean(), this.getAlertUser())!=0 && currentOfferFromAuction.getPrice()>offerBean.getPrice()){
							alert=new AlertBean(Messages.ALERT_TO + this.getAlertUser() + Messages.AUCTION_PRICE_CHANGED_MESSAGE + auctionBean.getProduct().getIdentifier() + " d'un montant de " + currentOfferFromAuction.getPrice() + " euros");
							break;
						}
					}
				}
			}
		}else if(obj instanceof AuctionStateEnum){
			AuctionStateEnum state=(AuctionStateEnum)obj;
			
			if(AlertType.ALERT_CANCELED_AUCTION.equals(this.getAlertType()) && !AlertType.ALERT_AUTOMATIC.equals(this.getAlertType())){

				if(state.equals(AuctionStateEnum.CANCELED)){
					
					alert=new AlertBean(Messages.ALERT_TO + this.getAlertUser() + Messages.AUCTION_CANCELED_MESSAGE + auctionBean.getProduct().getIdentifier());
				
				}
			} else {
				
				if(state.equals(AuctionStateEnum.COMPLETED)){
					//On cherche si l'offre a ete emportee par un utilisateur
					for(OfferBean offer : auctionBean.getListOfferBean()){
						if(offer.getPrice()>=auctionBean.getMinimumPrice()){
							if (offer.getPrice()<auctionBean.getReservePrice()) {
								System.out.println("L'enchere sur le produit "+auctionBean.getProduct().getIdentifier()+" est terminee et personne ne l'a remporte (le prix de reserve n'a pas ete attteint)");
							}
							else {
								System.out.println("L'enchere sur le produit "+auctionBean.getProduct().getIdentifier()+" est terminee et elle a ete emportee par "+offer.getUserBean().toString()+" pour la somme de "+ offer.getPrice() +" euros");
							}
							
							break;
						}
					}
				}
			}
		}
		//On ajoute l'alert sur la liste des alertes de l'utilisateur
		if (alert != null) {
			this.getAlertUser().getListAlertBean().add(alert);
		}
		
		return alert;
	}
}
