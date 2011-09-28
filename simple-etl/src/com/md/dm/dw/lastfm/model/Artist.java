/**
 * 
 */
package com.md.dm.dw.lastfm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author diego
 * 
 */
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long artistID;
	private String name;
	private String url;
	private String pictureURL;

	Artist() {
		// Just for ORM!!!
	}

	public Artist(Long artistID, String name, String url, String pictureURL) {
		super();
		this.artistID = artistID;
		this.name = name;
		this.url = url;
		this.pictureURL = pictureURL;
	}

	public Long getId() {
		return id;
	}

	public Long getArtistID() {
		return artistID;
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
		return "Artist [id=" + id + ", artistID=" + artistID + ", name=" + name
				+ ", url=" + url + ", pictureURL=" + pictureURL + "]";
	}

}
