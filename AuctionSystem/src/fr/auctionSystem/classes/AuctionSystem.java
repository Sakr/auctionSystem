package fr.auctionSystem.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.comparator.UserComparator;
import fr.auctionSystem.util.Messages;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author slimem
 *
 */
public class AuctionSystem {
	
	
	private Map<String,User> userMap=new  HashMap<String,User>();
	private Map<Integer,AuctionBean>  listVisilbleAuctionBean=new HashMap<Integer,AuctionBean>();
	
	/**
	 * @param auctionSystemManager
	 */
	public AuctionSystem() {
		
	}

	/**role
	 * @param login
	 * @param firstName
	 * @param secondName
	 * @param role 
	 * @return if user created True , else false 
	 */
	public boolean createUser(String login, String firstName, String secondName, RoleEnum role){
		//On créer un utilisateur 
		User user=new User(login,firstName,secondName,role);
		System.out.println(user.toString());
		//Si il n'existe pas on l'ajoute a la map des users
		if(isNotInMap(user,userMap)){
			userMap.put(login,user);
		}else{
			System.out.println(Messages.USER_ALREADY_EXIST);
		}
		return isNotInMap(user,userMap);
	}
	
	/**
	 * @param user
	 * @param userMap
	 * @return boolean 
	 */
	private boolean isNotInMap(User user, Map<String, User> userMap) {
		UserComparator userCamparator=new UserComparator();
		boolean Exist=true;
		for (String mapKey : userMap.keySet()) {
			 if(userCamparator.compare(user,userMap.get(mapKey))!=0){
				 Exist=false;
			 }
		}
		return Exist;
	}
	
	/**
	 * @param login
	 * @return the user
	 */
	public User getUserByLogin(String login){
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
	 * @return the userMap
	 */
	public Map<String, User> getUserMap() {
		return userMap;
	}

	/**
	 * @param userMap the userMap to set
	 */
	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	/**
	 * @return the listVisilbleAuctionBean
	 */
	public Map<Integer, AuctionBean> getListVisilbleAuctionBean() {
		for (String mapKey : userMap.keySet()) {
			Map<Integer,AuctionBean> mapvisibleAuctionByUser=userMap.get(mapKey).getListVisilbleAuctionBean();
			AuctionBean auction=null;
			for (Integer visibleAuctionByUserKey : mapvisibleAuctionByUser.keySet()) {
				auction= mapvisibleAuctionByUser.get(visibleAuctionByUserKey);
				listVisilbleAuctionBean.put(auction.getAuctionId(), auction) ;
			}
			 
		}
		return listVisilbleAuctionBean;
	}


}
