/**
 * 
 */
package fr.auctionSystem.interfaces;

import java.util.Map;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.classes.User;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author Ines
 *
 */
public interface AuctionSystemInterface {
	public boolean createUser(String login, String firstName, String secondName, RoleEnum role);
	public Map<Integer, AuctionBean> getListOfVisilbleAuctionForUser(User user);
	public User getUserByLogin(String login);
	
}
