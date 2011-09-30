package com.md.dm.dw.lastfm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Tag.all", query = "SELECT T FROM Tag T")
public class Tag {
	@Id
	@Column(name = "tag_id")
	private Long tagID;
	private String tagValue;

	Tag() {
		// For use only with ORMs
	}

	public Tag(Long tagID, String tagValue) {
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
		return "Tag [tagID=" + tagID + ", tagValue=" + tagValue + "]";
	}
}
