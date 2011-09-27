/**
 * 
 */
package com.md.dm.dw.lastfm.model;

/**
 * @author diego
 *
 */
public class Artist {

	private Long id;
	private Long lastfmId;
	private String name;
	private String url;
	private String pictureURL;
	
	Artist() {
		// Just for ORM!!!
	}

	public Artist(Long lastfmId, String name, String url, String pictureURL) {
		super();
		this.lastfmId = lastfmId;
		this.name = name;
		this.url = url;
		this.pictureURL = pictureURL;
	}

	public Long getId() {
		return id;
	}

	public Long getLastfmId() {
		return lastfmId;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", lastfmId=" + lastfmId + ", name=" + name
				+ ", url=" + url + ", pictureURL=" + pictureURL + "]";
	}
	
	
}
