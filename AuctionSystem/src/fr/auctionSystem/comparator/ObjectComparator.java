package fr.auctionSystem.comparator;

import java.util.Comparator;

import fr.auctionSystem.classes.User;
import fr.auctionSystem.util.Clock;

/**
 * @author 
 * Comprateur d'objet
 */
public class ObjectComparator implements Comparator<Object> {

	public ObjectComparator() {
	}

	@Override
	public int compare(Object o1, Object o2) {
		boolean comparatorReturn=false;
		
		//Si l'objet passé en parametre est un user
		if(o1 instanceof User){
			User user= (User)o1;
			User user2= (User)o2;
			comparatorReturn= user.getLogin().equalsIgnoreCase(user2.getLogin());
		}//Si l'objet passé en parametre est une horloge
		else if(o1 instanceof Clock){
			
			Clock horloge=(Clock)o1;
			Clock horloge2=(Clock)o2;
			comparatorReturn= horloge.getCurrentDate().equals(horloge2.getCurrentDate());
		}else{
			comparatorReturn=o1.equals(o2);
		}
		return ((comparatorReturn) ? 0 : 1);
	}

}
