package fr.auctionSystem.classes;

import java.util.HashMap;
import java.util.Map;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.OfferBean;
import fr.auctionSystem.comparator.ObjectComparator;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Messages;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author 
 *
 */
public class AuctionSystem {

	private Map<String,User> userMap=new  HashMap<String,User>();
		
	public AuctionSystem() {
		
	}
	
	/**
	 * 
	 * @param login
	 * @param firstName
	 * @param secondName
	 * @param role
	 * @return True si l'utilisateur a ete cree, False sinon 
	 */
	public boolean createUser(String login, String firstName, String secondName, RoleEnum role) {
		//On creer un utilisateur 
		User user=new User(login,firstName,secondName,role);
		//Si il n'existe pas on l'ajoute a la map des users
		if(!isInMap(user)){
			userMap.put(login,user);
			System.out.println(Messages.USER_CREATED);
			return true;
		}else{
			System.out.println(Messages.USER_ALREADY_EXIST);
			return false;
		}
	}
	
	/**
	 * Cette methode prend en parametre un utilisateur, et retourne la liste de toutes les encheres visible pour un lui
	 * 
	 * cette liste sera composé de deux parties:
	 * 
	 * - Une partie visible par tous les utilisateurs du systeme composee des encheres publiees
	 * - Une partie annulee visible que par l'utilisateur ayant emis cette offre, et par les utilisateurs qui ont emis au moins une offre dessus
	 *  
	 */
	public Map<Integer, AuctionBean> getListOfVisilbleAuctionForUser(User user) {
		Map<Integer,AuctionBean>  listVisilbleAuctionBean=new HashMap<Integer,AuctionBean>();
		ObjectComparator userComparator=new ObjectComparator();
		//On recupere la liste des utilisateurs 
		User userFromMap=null;
		Map<Integer,AuctionBean> mapAuctionByUser=null;
		for (String mapKey : userMap.keySet()) {
			
			//On recupere la liste des encheres publiees pour chacun des utilisateurs
			userFromMap=userMap.get(mapKey);
			mapAuctionByUser=userFromMap.getListAuctionBean();

			//Si l'utilisateur possede des encheres
			if(!mapAuctionByUser.isEmpty()){
				AuctionBean auction=null;
				//On parcoure la liste des encheres par user
				for (Integer auctionByUserKey : mapAuctionByUser.keySet()) {
					auction= mapAuctionByUser.get(auctionByUserKey);
					
					//Si l'enchere est publiee elle est visible pour tous
					if(auction.getState().equals(AuctionStateEnum.PUBLISHED)){
						listVisilbleAuctionBean.put(auction.getAuctionId(), auction);
					//Si l'enchere est creee elle est visible uniquement pour le vendeur
					}else if(auction.getState().equals(AuctionStateEnum.CREATED) && userComparator.compare(userFromMap, user) == 0){
						listVisilbleAuctionBean.put(auction.getAuctionId(), auction);
					//Si l'enchere est annulee elle est visible uniquement pour le vendeur et tous ceux qui ont poses une offre sur l'enchere
					}else if(auction.getState().equals(AuctionStateEnum.CANCELED)){
						//Si au moins un acheteur a mis une offre sur l'enchere
						if (!auction.getListOfferBean().isEmpty()) {
							//Pour chaque offre on verifie si le user en parametre est celui qui doit voir l'enchere
							for(OfferBean offer : auction.getListOfferBean()){
								if(userComparator.compare(userFromMap, user) == 0 || userComparator.compare(offer.getUserBean(), user) == 0){
									listVisilbleAuctionBean.put(auction.getAuctionId(), auction);
								}
							}
						}
						//Si il n'y a que le vendeur
						else {
							if(userComparator.compare(userFromMap, user) == 0) {
								listVisilbleAuctionBean.put(auction.getAuctionId(), auction);
							}
						}

					}
				}
			}
		}
		
		return listVisilbleAuctionBean;
	}

	/**
	 * 
	 * @param login
	 * @return l'utilisateur 
	 */
	public User getUserByLogin(String login) {
		User user=null;
		//On parcour l'hashmap pour chercher l'utilisateur en fonction de son login 
		for (String mapKey : userMap.keySet()) {
			if(mapKey.equals(login)){
				 user=userMap.get(mapKey);
				 break;
			 }
		}
		if(user==null){
			System.out.println(Messages.USER_NOT_EXIST);
		}
		return user;
	}

	/**
	 * @param user
	 * @return boolean 
	 * Cette methode verifie si l'utilisateur qu'on essaye de creer est sur la liste
	 */
	private boolean isInMap(User user) {
		ObjectComparator userCamparator=new ObjectComparator();
		boolean Exist=false;
		for (String mapKey : userMap.keySet()) {
			 if(userCamparator.compare(user,userMap.get(mapKey))==0){
				 Exist=true;
				 break;
			 }
		}
		return Exist;
	}

}
