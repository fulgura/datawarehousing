package com.md.dm.dw.lastfm.valueobject;

import java.util.Date;

/**
 * <pre>
 * userID	artistID	tagID	timestamp
 * 2	52	13	1238536800000
 * </pre>
 * 
 * @author diego
 * 
 */
public class UserTaggedArtistTimestamp {

	private long userID;
	private long artistID;
	private long tagID;
	private Date timestamp;
	public UserTaggedArtistTimestamp(long userID, long artistID, long tagID,
			Date timestamp) {
		super();
		this.userID = userID;
		this.artistID = artistID;
		this.tagID = tagID;
		this.timestamp = timestamp;
	}
	public long getUserID() {
		return userID;
	}
	public long getArtistID() {
		return artistID;
	}
	public long getTagID() {
		return tagID;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	@Override
	public String toString() {
		return "UserTaggedArtistTimestamp [userID=" + userID + ", artistID="
				+ artistID + ", tagID=" + tagID + ", timestamp=" + timestamp
				+ "]";
	}
}
