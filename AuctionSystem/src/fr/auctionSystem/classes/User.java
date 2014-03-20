/**
 * 
 */
package fr.auctionSystem.classes;


import java.util.Map;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.UserBean;
import fr.auctionSystem.interfaces.SellerRole;
import fr.auctionSystem.manager.AuctionManager;
import fr.auctionSystem.util.RoleEnum;
/**
 * @author slimem
 *
 */
public class User extends UserBean implements SellerRole{

	private Map<Integer,AuctionBean> mapAuctionBean;
	
	public User(String login, String firstName, String secondName, RoleEnum role) {
		super(login, firstName, secondName, role);
	}

	@Override
	public void issueOffer(AuctionBean auction) {
	}

	@Override
	public AuctionBean createAuction() {
		AuctionManager auctionManager=new AuctionManager();
		AuctionBean auction=auctionManager.getAuctionBean();
		//On ajoute l'enchere sur la liste des encheres du User
		mapAuctionBean.put(auction.getAuctionId(),auction);
		return auction;
	}

	@Override
	public void postAuction(AuctionBean auction) {
	}

	@Override
	public void fixMinimumPrice(AuctionBean auction) {
	}

	@Override
	public void cancelAuction(AuctionBean auction) {
	}

}
