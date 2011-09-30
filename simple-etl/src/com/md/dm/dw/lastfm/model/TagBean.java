package com.md.dm.dw.lastfm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "D_TAG")
@NamedQuery(name = "TagBean.all", query = "SELECT T FROM TagBean T")
public class TagBean {
	@Id
	@Column(name = "tag_id")
	private Long tagID;
	private String tagValue;

	TagBean() {
		// For use only with ORMs
	}

	public TagBean(Long tagID, String tagValue) {
		super();
		this.tagID = tagID;
		this.tagValue = tagValue;
	}

	public Long getTagID() {
		return tagID;
	}

	public String getTagValue() {
		return tagValue;
	}

	@Override
	public String toString() {
		return "TagBean [tagID=" + tagID + ", tagValue=" + tagValue + "]";
	}
}
