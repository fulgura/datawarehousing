/**
 * 
 */
package com.md.dm.dw.lastfm.valueobject;

/**
 * <pre>
 * userID artistID tagID day month year 
 * 2 52 13 1 4 2009
 * </pre>
 * 
 * @author diego
 * 
 */
public class UserTaggedArtist {

	private long userID;
	private long artistID;
	private long tagID;
	private int day;
	private int month;
	private int year;

	public UserTaggedArtist(long userID, long artistID, long tagID, int day,
			int month, int year) {
		super();
		this.userID = userID;
		this.artistID = artistID;
		this.tagID = tagID;
		this.day = day;
		this.month = month;
		this.year = year;
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

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "UserTaggedArtist [userID=" + userID + ", artistID=" + artistID
				+ ", tagID=" + tagID + ", day=" + day + ", month=" + month
				+ ", year=" + year + "]";
	}
}
