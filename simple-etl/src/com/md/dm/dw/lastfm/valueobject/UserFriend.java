/**
 * 
 */
package com.md.dm.dw.lastfm.valueobject;

/**
 * 
 * Value object to retrieve info from artist_friend.dat <BR>
 * 
 * <pre>
 * userID friendID 
 * 2 	275
 * </pre>
 * 
 * @author diego
 * 
 */
public class UserFriend {

	private long userid;
	private long fiendid;

	public UserFriend(long userid, long fiendid) {
		super();
		this.userid = userid;
		this.fiendid = fiendid;
	}

	public long getUserid() {
		return userid;
	}

	public long getFiendid() {
		return fiendid;
	}

	@Override
	public String toString() {
		return "UserFriend [userid=" + userid + ", fiendid=" + fiendid + "]";
	}
}
