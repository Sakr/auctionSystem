/**
 * 
 */
package fr.auctionSystem.comparator;

import java.util.Comparator;

import fr.auctionSystem.classes.User;
import fr.auctionSystem.util.Horloge;

/**
 * @author slimem
 *
 */
public  class ObjectComparator implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		
		boolean comparatorReturn=false;
		
		if(o1 instanceof User){
			User user= (User)o1;
			User user2= (User)o2;
			comparatorReturn= user.getLogin().equalsIgnoreCase(user2.getLogin());
		}
		else if(o1 instanceof Horloge){
			
			Horloge horloge=(Horloge)o1;
			Horloge horloge2=(Horloge)o2;
			comparatorReturn= horloge.getCurrentDateHour().equalsIgnoreCase(horloge2.getCurrentDateHour());
		}
		return ((comparatorReturn) ? 0 : 1);
	}
	
}