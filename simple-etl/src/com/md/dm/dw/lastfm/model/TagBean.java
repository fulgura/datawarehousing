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

import de.umass.lastfm.Tag;

@Entity
@Table(name = "D_TAG")
@NamedQuery(name = "TagBean.all", query = "SELECT T FROM TagBean T")
public class TagBean {
	@Id
	@Column(name = "tag_id")
	private Long tagID;
	private String tagValue;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date wikiLastChanged;
	@Column(length = 2048)
	private String wikiSummary;
	@Column(length = 8000)
	private String wikiText;

	@OneToMany(targetEntity = TagBean.class, cascade = CascadeType.ALL)
	private List<TagBean> similarTagList;

	TagBean() {
		// For use only with ORMs
	}

	public TagBean(Long tagID, String tagValue) {
		super();
		this.tagID = tagID;
		this.tagValue = tagValue;
		this.creationDate = new Date();
		this.similarTagList = new ArrayList<TagBean>();
		this.wikiLastChanged = null;
		this.wikiSummary = null;
		this.wikiText = null;

	}

	public Long getTagID() {
		return tagID;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void addTagInfo(Tag tagInfo) {
		if (tagInfo != null) {
			this.setWikiLastChanged(tagInfo.getWikiLastChanged());
			this.setWikiSummary(tagInfo.getWikiSummary());
			this.setWikiText(tagInfo.getWikiText());
		}
	}

	public Date getWikiLastChanged() {
		return wikiLastChanged;
	}

	public void setWikiLastChanged(Date wikiLastChanged) {
		this.wikiLastChanged = wikiLastChanged;
	}

	public String getWikiSummary() {
		return wikiSummary;
	}

	public void setWikiSummary(String wikiSummary) {
		this.wikiSummary = wikiSummary;
	}

	public String getWikiText() {
		return wikiText;
	}

	public void setWikiText(String wikiText) {
		this.wikiText = wikiText;
	}

	public List<TagBean> getSimilarTagList() {
		return similarTagList;
	}

	public void setSimilarTagList(List<TagBean> similarTagList) {
		this.similarTagList = similarTagList;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	@Override
	public String toString() {
		return "TagBean [tagID=" + tagID + ", tagValue=" + tagValue
				+ ", creationDate=" + creationDate + ", wikiLastChanged="
				+ wikiLastChanged + ", wikiSummary=" + wikiSummary
				+ ", wikiText=" + wikiText + ", similarTagList="
				+ similarTagList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagID == null) ? 0 : tagID.hashCode());
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
		TagBean other = (TagBean) obj;
		if (tagID == null) {
			if (other.tagID != null)
				return false;
		} else if (!tagID.equals(other.tagID))
			return false;
		return true;
	}
	
	
}
