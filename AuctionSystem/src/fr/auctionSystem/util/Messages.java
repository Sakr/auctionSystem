/**
 * 
 */
package fr.auctionSystem.util;

/**
 * @author Sakr
 *
 */
public final class Messages {
	/**Messages for auction**/
	public static final String USER_ALREADY_EXIST="L'utilisateur existe deja�";
	public static final String USER_NOT_EXIST="L'utilisateur n'existe pas";
	public static final String AUCTION_ALREADY_PUBLISHED ="L'enchere est deja� publiee";
	public static final String AUCTION_NOT_BELONG_TO_USER = "Cette enchere n'appartient pas a� cette utilisateur";
	public static final String AUCTION_ALREADY_CANCELED = "Cette enchere est deja� annuleeS";
	public static final String NO_VISIBLE_AUCTION = "Aucune enchere n'est visible";
	public static final String NOT_YOUR_AUCTION = "Cette enchere ne vous appartient pas, vous n'avez pas le droit de consulter son prix de reserve";
	public static final String NO_RIGHT_CREATE_AUCTION = "Vous n'avez pas le droit de creer des encheres";
	public static final String NO_RIGHT_POST_AUCTION = "Vous n'avez pas le droit de publier cette enchere";
	public static final String NO_RIGHT_CANCEL_AUCTION = "Vous n'avez pas le droit d'annuler cette encheres";
	public static final String NO_RIGHT_ISSUE_OFFER ="Vous n'avez pas le droit d'emmettre des offres sur une enchere qui vous appartient";
	public static final String NO_RIGHT_ISSUE_OFFER_NOT_PUBLISHED="Vous n'avez pas le droit d'emmettre des offres sur cette enchere, elle n'est pas encore publiee";
	public static final String NO_RIGHT_ISSUE_OFFER_BELOW_MINIMUM_PRICE="Il n'est pas possible d'emmettre une offre au dessous du prix minimum";
	public static final String NO_RIGHT_ADD_ALERT_AUCTION = "Vous n'avez pas le droit d'ajouter des alertes en etant que vendeur";
	
	public static final String AUCTION_CANCELED_MESSAGE="[ALERT] Le vendeur a annul� l'enchere du produit ";
	public static final String AUCTION_PRICE_CHANGED_MESSAGE="[ALERT] Une offre superieure a la votre a ete emise sur l'enchere du produit ";
	public static final String RESERVE_PRICE_REACHED_BY_OFFER = "[ALERT] Vous ne pouvez pas annuler cette enchere, une offre dessus a atteint le prix de reserve";
	public static final String OFFER_DONE_ON_AUCTION="[ALERT] Une offre a ete emise sur l'enchere du produit ";
	public static final String OFFER_DONE_ON_AUCTION_PRODUCT=" d'un montant de ";
	public static final String AUCTION_OFFER_DONE_MESSAGE="Une offre est faite sur votre enchere";
	
	public static final String NO_SLLER_ROLE = "Vous devez avoir un role vendeur pour effectuer cette action";
	public static final String AUCTION_BELONG_TO_USER = "Vous ne pouvez pas ajouter une alert sur une enchere qui vous appartient";
	
	/** Alert messages **/
	public static final String ALERT_TO="[TO] ";
	public static final String DEVISE=" euros";
	public static final String AUCTION_CREATED = "L'enchere a bien ete creee";
	public static final String AUCTION_PUBLISHED =  "L'enchere a bien ete publiee";
	public static final String AUCTION_IS_PUBLISHED = "L'enchere est publi�e, il n'est plus possible de la modifier";
}
