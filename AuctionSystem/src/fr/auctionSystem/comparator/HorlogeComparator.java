/**
 * 
 */
package fr.auctionSystem.comparator;

import java.util.Comparator;

import fr.auctionSystem.util.Horloge;

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
