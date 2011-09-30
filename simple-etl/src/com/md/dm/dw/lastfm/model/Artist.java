/**
 * 
 */
package com.md.dm.dw.lastfm.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author diego
 * 
 */
@Entity
@NamedQuery(name = "Artist.all", query = "SELECT A FROM Artist A")
public class Artist {

	@Id
	@Column(name = "artist_id")
	private Long artistID;
	private String name;
	private String url;
	private String pictureURL;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@OneToMany(targetEntity = Artist.class, cascade = CascadeType.ALL)
	private List<Artist> similarArtistList;
	@OneToMany(targetEntity = Tag.class, cascade = CascadeType.ALL)
	private List<Tag> tagList;

	Artist() {
		// Just for ORM!!!
	}

	public Artist(Long artistID, String name, String url, String pictureURL) {
		super();
		this.artistID = artistID;
		this.name = name;
		this.url = url;
		this.pictureURL = pictureURL;
		this.creationDate = new Date();
	}

	@Override
	public String toString() {
		return "Artist [artistID=" + artistID + ", name=" + name + ", url="
				+ url + ", pictureURL=" + pictureURL + ", creationDate="
				+ creationDate + ", similarArtistList=" + similarArtistList
				+ ", tagList=" + tagList + "]";
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

	public Date getCreationDate() {
		return creationDate;
	}

	public List<Artist> getSimilarArtistList() {
		return similarArtistList;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

}
