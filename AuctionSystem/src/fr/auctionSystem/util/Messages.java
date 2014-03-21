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
	public static final String USER_ALREADY_EXIST="L'utilisateur existe dèjà";
	public static final String USER_NOT_EXIST="L'utilisateur n'existe pas";
	public static final String AUCTION_ALREADY_PUBLISHED ="L'enchere est dèjà publiée";
	public static final String AUCTION_NOT_BELONG_TO_USER = "Cette enchere n'appartient pas à ette utilisateur";
	public static final String AUCTION_ALREADY_CANCELED = "Cette enchére est dèjà annuléeS";
	public static final String NO_VISIBLE_AUCTION = "Aucune enchere n'est visible";
	public static final String NOT_YOUR_AUCTION = "Cette enchere ne vous appartient pas, vous n'avez pas le droit de consulter son prix de reserve";
	public static final String NO_RIGHT_CREATE_AUCTION = "Vous n'avez pas le droit de creer des encheres";
	public static final String NO_RIGHT_POST_AUCTION = "Vous n'avez pas le droit de publier cette enchere";
	public static final String NO_RIGHT_CANCEL_AUCTION = "Vous n'avez pas le droit d'annuler cette encheres";
	
	/** Messages for buyer **/
	public static final String AUCTION_CANCELED_MESSAGE="L'enchere a ete annulee par le vendeur";
	public static final String AUCTION_RESERVE_PRICE_REACHED_MESSAGE="Le prix de reserve a ete atteint par une offre";
	public static final String AUCTION_PRICE_CHANGED_MESSAGE="Une offre superieure e celle emise par l'acheteur a ete emise par un autre acheteur";
	
	/** Messages for seller **/
	public static final String AUCTION_OFFER_DONE_MESSAGE="Une offre est faite sur votre enchere";
	
}
