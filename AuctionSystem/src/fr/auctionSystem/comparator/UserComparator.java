/**
 * 
 */
package fr.auctionSystem.comparator;

import java.util.Comparator;

import fr.auctionSystem.classes.User;

/**
 * @author slimem
 *
 */
public  class UserComparator implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		return o1.getLogin().compareTo(o2.getLogin());
	}
	
}