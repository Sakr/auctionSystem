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
	
	/** Messages for buyer **/
	public static final String AUCTION_CANCELED_MESSAGE="L'enchere a ete annulee par le vendeur";
	public static final String AUCTION_RESERVE_PRICE_REACHED_MESSAGE="Le prix de reserve a ete atteint par une offre";
	public static final String AUCTION_PRICE_CHANGED_MESSAGE="Une offre superieure e celle emise par l'acheteur a ete emise par un autre acheteur";
	
	/** Messages for seller **/
	public static final String AUCTION_OFFER_DONE_MESSAGE="Une offre est faite sur votre enchere";
	
}
