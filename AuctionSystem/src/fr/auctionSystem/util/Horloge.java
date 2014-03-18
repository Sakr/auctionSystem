/**
 * 
 */
package fr.auctionSystem.util;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * @author slimem
 * 
 */
public class Horloge{
	  //On récupère l'instance d'un calendrier 
	  //Elle va nous permettre de récupérer l'heure actuelle
	  private Calendar cal;
	  private String hour = "";
		
	  public void run() {
	    while(true){
	      this.cal = Calendar.getInstance();
	      this.hour =  //Les heures
	        this.cal.get(Calendar.HOUR_OF_DAY) + " : " 
	        + 
	        (      //Les minutes
	          this.cal.get(Calendar.MINUTE) < 10
	          ? "0" + this.cal.get(Calendar.MINUTE)
	          : this.cal.get(Calendar.MINUTE)
	        )
	        + " : " 
	        +
	        (      //Les secondes
	          (this.cal.get(Calendar.SECOND)< 10) 
	          ? "0"+this.cal.get(Calendar.SECOND) 
	          : this.cal.get(Calendar.SECOND)
	        );
				
	      try {
	        Thread.sleep(1000);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	  }

	}