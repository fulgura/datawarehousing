/**
 * 
 */
package com.md.dm.dw.lastfm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author diego
 * 
 */
@Entity
@IdClass(TaggingBeanId.class)
@Table(name = "F_TAGGING")
// @NamedQuery(name = "TaggingBean.all", query = "SELECT A FROM TaggingBean A")
public class TaggingBean implements Serializable {

	@Id
	private Long tagBeanId;
	@Id
	private Long userBeanId;
	@Id
	private Long artistBeanId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taggingDate;

	TaggingBean() {
		// ORM uses it :(
	}

	public TaggingBean(Long tagBeanId, Long userBeanId, Long artistBeanId,
			Date taggingDate) {
		super();
		this.tagBeanId = tagBeanId;
		this.userBeanId = userBeanId;
		this.artistBeanId = artistBeanId;
		this.taggingDate = taggingDate;
	}

	public Long getTagBeanId() {
		return tagBeanId;
	}

	public Long getUserBeanId() {
		return userBeanId;
	}

	public Long getArtistBeanId() {
		return artistBeanId;
	}

	public Date getTaggingDate() {
		return taggingDate;
	}

}
