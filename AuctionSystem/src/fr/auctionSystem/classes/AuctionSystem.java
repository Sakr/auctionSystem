package fr.auctionSystem.classes;

import java.util.HashMap;
import java.util.Map;

import fr.auctionSystem.manager.AuctionSystemManager;
import fr.auctionSystem.util.Messages;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author slimem
 *
 */
public class AuctionSystem {
	
	
	private Map<String,User> userMap=new  HashMap<String,User>();
	
	private AuctionSystemManager auctionSystemManager;
	
	/**
	 * @param auctionSystemManager
	 */
	public AuctionSystem(AuctionSystemManager auctionSystemManager) {
		this.auctionSystemManager = auctionSystemManager;
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
		if(auctionSystemManager.isNotInMap(user,userMap)){
			userMap.put(login,user);
		}else{
			System.out.println(Messages.USER_ALREADY_EXIST);
		}
		return auctionSystemManager.isNotInMap(user,userMap);
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
	
	
	
}
