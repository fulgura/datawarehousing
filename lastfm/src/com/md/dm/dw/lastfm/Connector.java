/**
 * 
 */
package com.md.dm.dw.lastfm;

import de.umass.lastfm.Authenticator;
import de.umass.lastfm.Session;

/**
 * @author diego
 * 
 */
public class Connector {

	private String key; // api key
	private String secret; // api secret
	private String user; // user name
	private String password; // user's password
	private Session session;

	public Connector(String user, String password, String key, String secret) {
		super();
		this.key = key;
		this.secret = secret;
		this.user = user;
		this.password = password;
		session = Authenticator.getMobileSession(user, password, key, secret);
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
