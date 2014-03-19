/**
 * 
 */
package fr.auctionSystem.util;

import java.util.Comparator;

/**
 * @author slimem
 *
 */
public class HorlogeComparator implements Comparator<Horloge>{

	@Override
	public int compare(Horloge o1, Horloge o2) {
		return o1.getCurrentDateHour().compareTo(o2.getCurrentDateHour());
	}
	
}
