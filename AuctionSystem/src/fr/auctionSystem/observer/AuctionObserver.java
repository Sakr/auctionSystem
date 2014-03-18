/**
 * 
 */
package fr.auctionSystem.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import fr.auctionSystem.bean.AuctionBean;

/**
 * @author slimem
 *
 */


public class AuctionObserver implements PropertyChangeListener {
    public AuctionObserver(AuctionBean auctionBean) {
        auctionBean.addChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
    	Long oldPrice=(Long) event.getOldValue();
    	Long newPrice=(Long) event.getNewValue();
    	Long max=new Long("6");
    	//Teste Bidon
    	if(newPrice>=max){
    		System.out.println("Changed property: " + event.getPropertyName() + " [old -> "
                    + oldPrice + "] | [new -> " + newPrice +"]");
    	}
       
    }

} 