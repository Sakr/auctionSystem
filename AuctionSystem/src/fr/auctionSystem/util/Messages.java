package fr.auctionSystem.util;

/**
 * @author 
 *
 */
public final class Messages {

	//Messages pour les utilisateurs
	public static final String USER_CREATED = "L'utilisateur a bien ete cree";
	public static final String USER_ALREADY_EXIST="L'utilisateur existe deja";
	public static final String USER_NOT_EXIST="L'utilisateur n'existe pas";
	
	//Messages pour les encheres
	public static final String AUCTION_ALREADY_PUBLISHED ="L'enchere est deja publiee";
	public static final String AUCTION_NOT_BELONG_TO_USER = "Cette enchere n'appartient pas a cette utilisateur";
	public static final String AUCTION_ALREADY_CANCELED = "Cette enchere est deja annuleeS";
	public static final String NO_VISIBLE_AUCTION = "Aucune enchere n'est visible";
	public static final String NOT_YOUR_AUCTION = "Cette enchere ne vous appartient pas, vous n'avez pas le droit de consulter son prix de reserve";
	public static final String NO_RIGHT_CREATE_AUCTION = "Vous n'avez pas le droit de creer des encheres";
	public static final String NO_RIGHT_POST_AUCTION = "Vous n'avez pas le droit de publier cette enchere";
	public static final String NO_RIGHT_CANCEL_AUCTION = "Vous n'avez pas le droit d'annuler cette enchere";
	public static final String AUCTION_CREATED = "L'enchere a bien ete creee";
	public static final String AUCTION_PUBLISHED =  "L'enchere a bien ete publiee";
	public static final String AUCTION_IS_PUBLISHED = "L'enchere est publie, il n'est plus possible de la modifier";
	public static final String NO_RIGHT_CANCEL_AUCTION_RESERVE_PRICE = "Vous n'avez pas le droit d'annuler cette enchere car le prix de reserve est atteint par une offre";
	public static final String AUCTION_OFFER_DONE_MESSAGE="Une offre est faite sur votre enchere";
	public static final String NO_SLLER_ROLE = "Vous devez avoir un role vendeur pour effectuer cette action";
	
	//Messages pour les offres
	public static final String OFFER_DONE_ON_AUCTION_PRODUCT=" d'un montant de ";
	public static final String NO_RIGHT_ISSUE_OFFER ="Vous n'avez pas le droit d'emmettre des offres sur une enchere qui vous appartient";
	public static final String NO_RIGHT_ISSUE_OFFER_NOT_PUBLISHED="Vous n'avez pas le droit d'emmettre des offres sur cette enchere, elle n'est pas encore publiee";
	public static final String NO_RIGHT_ISSUE_OFFER_BELOW_MINIMUM_PRICE="Il n'est pas possible d'emmettre une offre egale ou en dessous du prix minimum";
	
	//Messages pour les alertes
	public static final String NO_RIGHT_ADD_ALERT_AUCTION = "Vous n'avez pas le droit d'ajouter des alertes en etant que vendeur";
	public static final String AUCTION_CANCELED_MESSAGE="[ALERT] Le vendeur a annule l'enchere du produit ";
	public static final String AUCTION_PRICE_CHANGED_MESSAGE="[ALERT] Une offre superieure a la votre a ete emise sur l'enchere du produit ";
	public static final String RESERVE_PRICE_REACHED_BY_OFFER = "[ALERT] Le prix de reserve a ete atteint sur l'enchere du produit ";
	public static final String OFFER_DONE_ON_AUCTION="[ALERT] Une offre a ete emise sur l'enchere du produit ";
	public static final String AUCTION_BELONG_TO_USER = "Vous ne pouvez pas ajouter une alert sur une enchere qui vous appartient";
	public static final String ALL_ALERT_REMOVE = "Toutes les alertes ont ete ete desactive sur l'enchere du produit ";
	public static final String ALL_ALERT_NOT_REMOVE = "Il n'y a pas d'alerte sur cette enchere pour l'utilisateur ";
	public static final String ALERT_ADD = "L'alerte a bien ete ajoutee";
	public static final String ALERT_REMOVE = "L'alerte a bien ete desactive";
	
	//Messages divers
	public static final String ALERT_TO="[TO] ";
	public static final String DEVISE=" euros";
}
