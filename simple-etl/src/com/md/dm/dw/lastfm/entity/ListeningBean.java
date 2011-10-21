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

@Entity
@Table(name = "F_LISTENING")
@NamedQuery(name = "ListeningBean.all", query = "SELECT A FROM ListeningBean A")
public class ListeningBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LISTENING_ID")
	private Long listeningId;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserBean userBean;
	
	@OneToOne
	@JoinColumn(name = "ARTIST_ID")
	private ArtistBean artistBean;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DATE_HIERARCHY_ID")
	private DateHierarchyBean dateHierarchyBean;
	
	public ListeningBean(UserBean userBean, ArtistBean artistBean, DateHierarchyBean dateHierarchyBean) {
		super();
		this.userBean = userBean;
		this.artistBean = artistBean;
		this.dateHierarchyBean = dateHierarchyBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public ArtistBean getArtistBean() {
		return artistBean;
	}

	public DateHierarchyBean getDateHierarchyBean() {
		return dateHierarchyBean;
	}

	@Override
	public String toString() {
		return "ListeningBean [listeningId=" + listeningId + ", userBean="
				+ userBean + ", artistBean=" + artistBean
				+ ", dateHierarchyBean=" + dateHierarchyBean + "]";
	}

	public Long getListeningId() {
		return listeningId;
	}

}
