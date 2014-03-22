package fr.auctionSystem.interfaces;

public interface BuyerAlerter {
	
	/**Le prix de réserve a été atteint par une offre**/
	public boolean addPriceReserveAlert(boolean value);
	/**L'enchère a été annulée par le vendeur.**/
	public boolean addAuctionCanceledBySellerAlert(boolean value);
	/**Une offre supérieure à celle émise par l'acheteur a été émise par un autre acheteur.**/
	public boolean addAnotherGreaterOfferAlert(boolean value);
}
