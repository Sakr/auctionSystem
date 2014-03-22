/**
 * 
 */
package fr.auctionSystem.main;


import java.util.Map;

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
		
		//On creer un utilisateur
		auctionSystem.createUser("login2", "firstName2", "secondName2",RoleEnum.SELLER);
		
		//On verifie si l'utisateur s'est bien ajouté
		User user=auctionSystem.getUserByLogin("login");
		System.out.println(user.toString());
		
		//On verifie si l'utisateur s'est bien ajouté
		User user2=auctionSystem.getUserByLogin("login2");
		System.out.println(user2.toString());
		
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
		
		AuctionBean auction2=user2.createAuction(AuctionStateEnum.CREATED,reservePriceHorloge , minimumPrice, reservePrice);
		//Produit bidon 2
		auction2.getProduct().setIdentifier("2- IPhone 5S");
		auction2.getProduct().setDescription("2- Tres bon état, 900 balles");
		//On poste le produit, il s'ajoute sur la liste des encheres disponible sur AuctionSystem
		user2.postAuction(auction2);
		
		
		//cette liste va chercher en fonction de l'utilisateur les encheres visibles, en masquant le prix de reserve sur les encheres qui ne lui appartient pas
		//Map<Integer, AuctionBean>  mapTesteVisible=auctionSystem.getListOfVisilbleAuctionBean(user2);
		Map<Integer, AuctionBean>  mapTesteVisible=auctionSystem.getListOfVisilbleAuctionBean(user2);
		AuctionBean visibleAuction;
		for (Integer mapKey : mapTesteVisible.keySet()) {
			 visibleAuction= mapTesteVisible.get(mapKey);
			 System.out.println(visibleAuction.toString());
		}
		
		//On l'annule
		user.cancelAuction(auction);
		//On l'annule
		user2.cancelAuction(auction2);
	}

}
