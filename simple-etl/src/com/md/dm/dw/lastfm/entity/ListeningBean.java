package com.md.dm.dw.lastfm.entity;

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
public class ListeningBean {

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artistBean == null) ? 0 : artistBean.hashCode());
		result = prime * result
				+ ((userBean == null) ? 0 : userBean.hashCode());
		result = prime * result + weight;
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
		ListeningBean other = (ListeningBean) obj;
		if (artistBean == null) {
			if (other.artistBean != null)
				return false;
		} else if (!artistBean.equals(other.artistBean))
			return false;
		if (userBean == null) {
			if (other.userBean != null)
				return false;
		} else if (!userBean.equals(other.userBean))
			return false;
		if (weight != other.weight)
			return false;
		return true;
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
