/**
 * 
 */
package com.md.dm.dw.lastfm.valueobject;

/**
 * Value Object to manage a line in user_artists.dat file.
 * <br>
 * <br>
 * <pre>2	51	13883</pre>
 * @author diego
 *
 */
public class UserArtistWeight {

	private long userid;
	private long artistid;
	private int weight;
	
	public UserArtistWeight(long userid, long artistid, int weight) {
		super();
		this.userid = userid;
		this.artistid = artistid;
		this.weight = weight;
	}

	public long getUserid() {
		return userid;
	}

	public long getArtistid() {
		return artistid;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "UserArtistWeight [userid=" + userid + ", artistid=" + artistid
				+ ", weight=" + weight + "]";
	}
	
	
}
