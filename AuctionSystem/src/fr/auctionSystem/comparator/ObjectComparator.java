/**
 * 
 */
package fr.auctionSystem.comparator;

import java.util.Comparator;

import fr.auctionSystem.classes.User;
import fr.auctionSystem.util.Clock;

/**
 * @author david
 *
 */
public class ObjectComparator implements Comparator<Object> {

	/**
	 * 
	 */
	public ObjectComparator() {
	}

	@Override
	public int compare(Object o1, Object o2) {
		boolean comparatorReturn=false;
		
		if(o1 instanceof User){
			User user= (User)o1;
			User user2= (User)o2;
			comparatorReturn= user.getLogin().equalsIgnoreCase(user2.getLogin());
		}
		else if(o1 instanceof Clock){
			
			Clock horloge=(Clock)o1;
			Clock horloge2=(Clock)o2;
			comparatorReturn= horloge.getCurrentDate().equals(horloge2.getCurrentDate());
		}
		return ((comparatorReturn) ? 0 : 1);
	}

}
