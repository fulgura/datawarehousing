package com.md.dm.dw.lastfm.entity;

import java.io.Serializable;

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
@Table(name = "D_LISTENING")
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
	private int weight;

	public ListeningBean(UserBean userBean, ArtistBean artistBean, int weight) {
		super();
		this.userBean = userBean;
		this.artistBean = artistBean;
		this.weight = weight;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public ArtistBean getArtistBean() {
		return artistBean;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "ListeningBean [userBean=" + userBean + ", artistBean="
				+ artistBean + ", weight=" + weight + "]";
	}

	public Long getListeningId() {
		return listeningId;
	}

}
