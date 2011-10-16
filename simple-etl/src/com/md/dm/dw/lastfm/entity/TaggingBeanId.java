package com.md.dm.dw.lastfm.entity;

import java.io.Serializable;

public class TaggingBeanId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long tagBeanId;
	private Long userBeanId;
	private Long artistBeanId;

	public TaggingBeanId() {
	}

	public Long getTagBeanId() {
		return tagBeanId;
	}

	public void setTagBeanId(Long tagBeanId) {
		this.tagBeanId = tagBeanId;
	}

	public Long getUserBeanId() {
		return userBeanId;
	}

	public void setUserBeanId(Long userBeanId) {
		this.userBeanId = userBeanId;
	}

	public Long getArtistBeanId() {
		return artistBeanId;
	}

	public void setArtistBeanId(Long artistBeanId) {
		this.artistBeanId = artistBeanId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artistBeanId == null) ? 0 : artistBeanId.hashCode());
		result = prime * result
				+ ((tagBeanId == null) ? 0 : tagBeanId.hashCode());
		result = prime * result
				+ ((userBeanId == null) ? 0 : userBeanId.hashCode());
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
		TaggingBeanId other = (TaggingBeanId) obj;
		if (artistBeanId == null) {
			if (other.artistBeanId != null)
				return false;
		} else if (!artistBeanId.equals(other.artistBeanId))
			return false;
		if (tagBeanId == null) {
			if (other.tagBeanId != null)
				return false;
		} else if (!tagBeanId.equals(other.tagBeanId))
			return false;
		if (userBeanId == null) {
			if (other.userBeanId != null)
				return false;
		} else if (!userBeanId.equals(other.userBeanId))
			return false;
		return true;
	}

	public TaggingBeanId(Long tagBeanId, Long userBeanId, Long artistBeanId) {
		super();
		this.tagBeanId = tagBeanId;
		this.userBeanId = userBeanId;
		this.artistBeanId = artistBeanId;
	}
}
