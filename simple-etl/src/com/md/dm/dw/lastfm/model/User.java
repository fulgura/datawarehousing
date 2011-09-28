/**
 * 
 */
package com.md.dm.dw.lastfm.model;

/**
 * @author diego
 * 
 */
public class User {

	private Long id;
	private Long userID;

	User() {
	}

	public User(Long userID) {
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
		return "User [id=" + id + ", userID=" + userID + "]";
	}

}
