/**
 * 
 */
package fr.auctionSystem.main;


import java.util.Date;

import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.classes.AuctionSystem;
import fr.auctionSystem.classes.User;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Clock;
import fr.auctionSystem.util.RoleEnum;

/**
 * @author Sakr
 *
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//On lance le AuctionSystem avec son Manager
		AuctionSystem auctionSystem=new AuctionSystem();
		
		//On creer un acheteur
		auctionSystem.createUser("login", "firstName", "secondName",RoleEnum.BUYER);
		
		//On creer un vendeur
		auctionSystem.createUser("login2", "firstName2", "secondName2",RoleEnum.SELLER);
		
		//On verifie si l'utisateur s'est bien ajouté
		User user=auctionSystem.getUserByLogin("login");
		System.out.println(user.toString());
		
		//On verifie si l'utisateur s'est bien ajouté
		User user2=auctionSystem.getUserByLogin("login2");
		System.out.println(user2.toString());
		
		//Cette utilisateur crée une enchere
		Date limitedDate=new Date();
		Long minimumPrice=new Long(10); 
		Long reservePrice=new Long(80);
		
		AuctionBean auction1=user2.createAuction(AuctionStateEnum.CREATED,limitedDate, new Clock(limitedDate), minimumPrice, reservePrice);
		//Produit bidon
		//On poste le produit, il s'ajoute sur la liste des encheres disponible sur AuctionSystem
		user.issueOffer(auction1, new Long(80));
		user2.postAuction(auction1);
		

	}

}
