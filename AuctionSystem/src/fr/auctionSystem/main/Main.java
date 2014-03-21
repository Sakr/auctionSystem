/**
 * 
 */
package fr.auctionSystem.main;


import fr.auctionSystem.bean.AuctionBean;
import fr.auctionSystem.classes.AuctionSystem;
import fr.auctionSystem.classes.User;
import fr.auctionSystem.util.AuctionStateEnum;
import fr.auctionSystem.util.Horloge;
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
		
		//On creer un utilisateur
		auctionSystem.createUser("login", "firstName", "secondName",RoleEnum.SELLER_BUYER);
		
		//On verifie si l'utisateur s'est bien ajouté
		User user=auctionSystem.getUserByLogin("login");
		System.out.println(user.toString());
		
		//Cette utilisateur crée une enchere
		Horloge reservePriceHorloge=new Horloge(2014,03,24,15,15,15);
		Long minimumPrice=new Long(10); 
		Long reservePrice=new Long(80);
		
		AuctionBean auction=user.createAuction(AuctionStateEnum.CREATED,reservePriceHorloge , minimumPrice, reservePrice);
		//Produit bidon
		auction.getProduct().setIdentifier("IPhone 5S");
		auction.getProduct().setDescription("Tres bon état, 900 balles");
		//On poste le produit, il s'ajoute sur la liste des encheres disponible sur AuctionSystem
		user.postAuction(auction);
		auctionSystem.getListOfVisilbleAuctionBean(user);
		//On l'annule
		user.cancelAuction(auction);
	}

}
