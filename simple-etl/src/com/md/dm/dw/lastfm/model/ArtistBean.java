/**
 * 
 */
package com.md.dm.dw.lastfm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author diego
 * 
 */
@Entity
@Table(name = "D_ARTIST")
@NamedQuery(name = "ArtistBean.all", query = "SELECT A FROM ArtistBean A")
public class ArtistBean {

	@Id
	@Column(name = "artist_id")
	private Long artistID;
	private String name;
	private String url;
	private String pictureURL;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@OneToMany(targetEntity = ArtistBean.class, cascade = CascadeType.ALL)
	private List<ArtistBean> similarArtistList;
	@OneToMany(targetEntity = TagBean.class, cascade = CascadeType.ALL)
	private List<TagBean> tagList;

	ArtistBean() {
		// Just for ORM!!!
	}

	public ArtistBean(Long artistID, String name, String url, String pictureURL) {
		super();
		this.artistID = artistID;
		this.name = name;
		this.url = url;
		this.pictureURL = pictureURL;
		this.creationDate = new Date();
		this.similarArtistList = new ArrayList<ArtistBean>();
		this.tagList = new ArrayList<TagBean>();
	}

	@Override
	public String toString() {
		return "ArtistBean [artistID=" + artistID + ", name=" + name + ", url="
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

	public List<ArtistBean> getSimilarArtistList() {
		return similarArtistList;
	}

	public List<TagBean> getTagList() {
		return tagList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artistID == null) ? 0 : artistID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtistBean other = (ArtistBean) obj;
		if (artistID == null) {
			if (other.artistID != null)
				return false;
		} else if (!artistID.equals(other.artistID))
			return false;
		return true;
	}

	
}
