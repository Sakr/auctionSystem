package fr.auctionSystem.classes;

import java.util.HashMap;
import java.util.Map;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.comparator.ObjectComparator;
import fr.auctionSystem.interfaces.AuctionSystemInterface;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Messages;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author slimem
 *
 */
public class AuctionSystem implements AuctionSystemInterface{
	
	private Map<String,User> userMap=new  HashMap<String,User>();
	private Map<Integer,AuctionBean>  listVisilbleAuctionBean=new HashMap<Integer,AuctionBean>();
	
	/**
	 * @param auctionSystemManager
	 */
	public AuctionSystem() {
		
	}

	@Override
	public boolean createUser(String login, String firstName,
			String secondName, RoleEnum role) {
		//On créer un utilisateur 
		User user=new User(login,firstName,secondName,role);
		System.out.println(user.toString());
		//Si il n'existe pas on l'ajoute a la map des users
		if(!isInMap(user)){
			userMap.put(login,user);
		}else{
			System.out.println(Messages.USER_ALREADY_EXIST);
		}
		return isInMap(user);
	}

	@Override
	public Map<Integer, AuctionBean> getListOfVisilbleAuctionForUser(User user) {
		ObjectComparator userComparator=new ObjectComparator();
		//On recupere la liste des utilisateurs 
		User userFromMap=null;
		Map<Integer,AuctionBean> mapAuctionByUser=null;
		for (String mapKey : userMap.keySet()) {
			
			//On recupere la liste des encheres publiees par chacun des utilisateurs
			userFromMap=userMap.get(mapKey);
			mapAuctionByUser=userFromMap.getListAuctionBean();
			
			//Si l'utilisateur possede des encheres publiees
			if(!mapAuctionByUser.isEmpty()){
				AuctionBean auction=null;
				//On les ajoutes sur la liste qui sera visible par tous les membres du systemes
				for (Integer auctionByUserKey : mapAuctionByUser.keySet()) {
					auction= mapAuctionByUser.get(auctionByUserKey);
					
					//Si l'encher est visible
					if(auction.getState().equals(AuctionStateEnum.PUBLISHED)){
						//Si l'utilisateur passe en parametre n'est pas celui qui possede la liste des enchers qu'on est entrain de parcourir
						//Il a pas le droit de voir le prix de reserve de l'enchere
						if(!(userComparator.compare(user, userFromMap)==0)){
							auction.setReservePrice(-1L);
						}
					}
					listVisilbleAuctionBean.put(auction.getAuctionId(), auction) ;
				}
			}
		}
		if(listVisilbleAuctionBean.isEmpty()){
			System.out.println(Messages.NO_VISIBLE_AUCTION);
		}
		return listVisilbleAuctionBean;
	}

	@Override
	public User getUserByLogin(String login) {
		User user=null;
		for (String mapKey : userMap.keySet()) {
			if(mapKey.equals(login)){
				 user=userMap.get(mapKey);
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
	 */
	private boolean isInMap(User user) {
		ObjectComparator userCamparator=new ObjectComparator();
		boolean Exist=false;
		for (String mapKey : userMap.keySet()) {
			 if(userCamparator.compare(user,userMap.get(mapKey))==0){
				 Exist=true;
			 }
		}
		return Exist;
	}
}
