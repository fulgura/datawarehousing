/**
 * 
 */
package com.md.dm.dw.lastfm.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author diego
 * 
 */
@Entity
@Table(name = "F_TAGGING")
@NamedQuery(name = "TaggingBean.all", query = "SELECT A FROM TaggingBean A")
public class TaggingBean {

	@OneToOne
	@JoinColumn(name = "ARTIST_BEAN")
	private ArtistBean artistBean;

	@OneToOne
	@JoinColumn(name = "TAG_BEAN")
	private TagBean tagBean;

	@OneToOne
	@JoinColumn(name = "USER_BEAN")
	private UserBean userBean;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taggingDate;

	public ArtistBean getArtistBean() {
		return artistBean;
	}

	public TagBean getTagBean() {
		return tagBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public Date getTaggingDate() {
		return taggingDate;
	}

	TaggingBean() {
		// ORM :)
	}
	public TaggingBean(ArtistBean artistBean, TagBean tagBean,
			UserBean userBean, Date taggingDate) {
		super();
		this.artistBean = artistBean;
		this.tagBean = tagBean;
		this.userBean = userBean;
		this.taggingDate = taggingDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artistBean == null) ? 0 : artistBean.hashCode());
		result = prime * result + ((tagBean == null) ? 0 : tagBean.hashCode());
		result = prime * result
				+ ((taggingDate == null) ? 0 : taggingDate.hashCode());
		result = prime * result
				+ ((userBean == null) ? 0 : userBean.hashCode());
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
		TaggingBean other = (TaggingBean) obj;
		if (artistBean == null) {
			if (other.artistBean != null)
				return false;
		} else if (!artistBean.equals(other.artistBean))
			return false;
		if (tagBean == null) {
			if (other.tagBean != null)
				return false;
		} else if (!tagBean.equals(other.tagBean))
			return false;
		if (taggingDate == null) {
			if (other.taggingDate != null)
				return false;
		} else if (!taggingDate.equals(other.taggingDate))
			return false;
		if (userBean == null) {
			if (other.userBean != null)
				return false;
		} else if (!userBean.equals(other.userBean))
			return false;
		return true;
	}

	
	
}
