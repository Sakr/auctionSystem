package fr.auctionSystem.alertes;

import fr.auctionSystem.bean.AlertBean;

public class AlertSeller implements AlertLauncher{

	@Override
	public void communicationWithUser(AlertBean alertBean) {
		System.out.println(alertBean.getMessage());
	}

}
