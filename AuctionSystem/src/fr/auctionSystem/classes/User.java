/**
 * 
 */
package fr.auctionSystem.classes;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.bean.ObjectBean;
import fr.auctionSystem.bean.UserBean;
import fr.auctionSystem.interfaces.SellerRole;
import fr.auctionSystem.manager.AuctionManager;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Horloge;
import fr.auctionSystem.util.Messages;
import fr.auctionSystem.util.RoleEnum;
/**
 * @author slimem
 *
 */
public class User extends UserBean implements SellerRole{

	private Map<Integer,AuctionBean> mapAuctionBean=new HashMap<Integer,AuctionBean>();
	private List<AuctionBean> listVisilbleAuctionBean=new ArrayList<AuctionBean>();
	
	public User(String login, String firstName, String secondName, RoleEnum role) {
		super(login, firstName, secondName, role);
	}

	@Override
	public void issueOffer(AuctionBean auction) {
	}

	@Override
	public AuctionBean createAuction(AuctionStateEnum state, Horloge deadLine, Long minimumPrice, Long reservePrice) {
		//
		AuctionManager auctionManager=new AuctionManager();
		//On crée l'enchere 
		ObjectBean product=new ObjectBean();
		AuctionBean auction=auctionManager.getAuctionBean(product,state,deadLine,minimumPrice,reservePrice);
		//On lui ajoute un id
		auction.setAuctionId();
		//On ajoute l'enchere sur la liste des encheres avec son id en clé de l'Hashmap
		mapAuctionBean.put(auction.getAuctionId(),auction);
		
		return auction;
	}

	@Override
	public boolean postAuction(AuctionBean auction) {
		
		//On verifie si l'enchere appartient a l'utilisateur
		if(mapAuctionBean.get(auction.getAuctionId())!=null){
			if(!auction.getState().equals(AuctionStateEnum.PUBLISHED)){
				auction.setState(AuctionStateEnum.PUBLISHED);
				listVisilbleAuctionBean.add(auction);
				return true;
			}else{
				System.out.println(Messages.AUCTION_ALREADY_PUBLISHED);
				return false;
			}
		}else{
			System.out.println(Messages.AUCTION_NOT_BELONG_TO_USER);
			return false;
		}
		
	}

	@Override
	public void fixMinimumPrice(AuctionBean auction) {
	}

	@Override
	public void cancelAuction(AuctionBean auction) {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [toString()=" + super.toString() + "]";
	}

	/**
	 * @return the listVisilbleAuctionBean
	 */
	public List<AuctionBean> getListVisilbleAuctionBean() {
		return listVisilbleAuctionBean;
	}
}
