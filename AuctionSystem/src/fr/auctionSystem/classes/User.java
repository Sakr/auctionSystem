/**
 * 
 */
package fr.auctionSystem.classes;

import fr.auctionSystem.bean.UserBean;
import fr.auctionSystem.interfaces.BuyerRole;
import fr.auctionSystem.interfaces.SellerRole;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author slimem
 *
 */
public class User extends UserBean implements SellerRole,BuyerRole{


	public User(String login, String firstName, String secondName, RoleEnum role) {
		super(login, firstName, secondName, role);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void issueOffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAuction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postAuction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fixMinimumPrice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelAuction() {
		// TODO Auto-generated method stub
		
	}

}
