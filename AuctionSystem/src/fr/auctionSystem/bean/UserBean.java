/**
 * 
 */
package fr.auctionSystem.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.auctionSystem.util.RoleEnum;

/**
 * @author Sakr
 *
 */
public class UserBean {
	
	private String login;
	private String firstName;
	private String secondName;
	private RoleEnum role;
	private List<AlertBean> listAlertBean;
	private Map<Integer,AuctionBean> listAuctionBean;
	
	/**
	 * @param login
	 * @param firstName
	 * @param secondName
	 * @param role
	 */
	public UserBean(String login, String firstName, String secondName,
			RoleEnum role) {
		this.login = login;
		this.firstName = firstName;
		this.secondName = secondName;
		this.role = role;
		this.listAlertBean=new ArrayList<AlertBean>();
		this.listAuctionBean=new HashMap<Integer,AuctionBean>();
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the secondName
	 */
	public String getSecondName() {
		return secondName;
	}
	/**
	 * @param secondName the secondName to set
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	/**
	 * @return the role
	 */
	public RoleEnum getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(RoleEnum role) {
		this.role = role;
	}
	
	/**
	 * @return the listAlertBean
	 */
	public List<AlertBean> getListAlertBean() {
		return listAlertBean;
	}


	/**
	 * @return the listAuctionBean
	 */
	public Map<Integer, AuctionBean> getListAuctionBean() {
		return listAuctionBean;
	}


	@Override
	public String toString() {
		return login + " ";
	}
}
