/**
 * 
 */
package com.md.dm.dw.lastfm.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.openjpa.persistence.jdbc.ForeignKey;

/**
 * @author diego
 * 
 */
@Entity
@Table(name = "F_TAGGING")
@NamedQuery(name = "TaggingBean.all", query = "SELECT A FROM TaggingBean A")
public class TaggingBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TAGGING_ID")
	private Long taggingId;

	@OneToOne
	@JoinColumn(name = "ARTIST_ID")
	private ArtistBean artistBean;

	@OneToOne
	@JoinColumn(name = "TAG_ID")
	private TagBean tagBean;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserBean userBean;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DATE_HIERARCHY_ID")
	private DateHierarchyBean dateHierarchyBean;

	public ArtistBean getArtistBean() {
		return artistBean;
	}

	public TagBean getTagBean() {
		return tagBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	TaggingBean() {
		// ORM :)
	}

	public TaggingBean(ArtistBean artistBean, TagBean tagBean,
			UserBean userBean, DateHierarchyBean taggingDate) {
		super();
		this.artistBean = artistBean;
		this.tagBean = tagBean;
		this.userBean = userBean;
		this.dateHierarchyBean = taggingDate;
	}

	public Long getTaggingId() {
		return taggingId;
	}

	public DateHierarchyBean getDateHierarchyBean() {
		return dateHierarchyBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((taggingId == null) ? 0 : taggingId.hashCode());
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
		if (taggingId == null) {
			if (other.taggingId != null)
				return false;
		} else if (!taggingId.equals(other.taggingId))
			return false;
		return true;
	}

	
}
