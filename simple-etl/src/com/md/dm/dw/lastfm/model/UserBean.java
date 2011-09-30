/**
 * 
 */
package com.md.dm.dw.lastfm.model;

import javax.persistence.Table;

/**
 * @author diego
 * 
 */
@Table(name = "D_USER")
public class UserBean {

	private Long id;
	private Long userID;

	UserBean() {
	}

	public UserBean(Long userID) {
		super();
		this.userID = userID;
	}

	public Long getId() {
		return id;
	}

	public Long getUserID() {
		return userID;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", userID=" + userID + "]";
	}

}
