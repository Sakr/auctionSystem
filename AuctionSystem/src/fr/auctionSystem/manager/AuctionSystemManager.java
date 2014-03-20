package fr.auctionSystem.manager;

import java.util.Map;

import fr.auctionSystem.classes.User;
import fr.auctionSystem.comparator.UserComparator;

public class AuctionSystemManager {

	public boolean isNotInMap(User user, Map<String, User> userMap) {
		UserComparator userCamparator=new UserComparator();
		boolean Exist=true;
		for (String mapKey : userMap.keySet()) {
			 if(userCamparator.compare(user,userMap.get(mapKey))!=0){
				 Exist=false;
			 }
		}
		return Exist;
	}
	
}
