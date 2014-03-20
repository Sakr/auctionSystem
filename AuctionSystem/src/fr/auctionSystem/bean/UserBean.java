/**
 * 
 */
package fr.auctionSystem.bean;

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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserBean [login=" + login + ", firstName=" + firstName
				+ ", secondName=" + secondName + ", role=" + role + "]";
	}
	
	
	
}
