package com.md.dm.dw.lastfm.model;

public class Tag {
	private Long id;
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

	public Long getId() {
		return id;
	}

	public Long getTagID() {
		return tagID;
	}

	public String getTagValue() {
		return tagValue;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", tagID=" + tagID + ", tagValue=" + tagValue
				+ "]";
	}

}
