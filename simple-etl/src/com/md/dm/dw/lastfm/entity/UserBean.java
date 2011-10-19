/**
 * 
 */
package com.md.dm.dw.lastfm.entity;

import java.io.Serializable;
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

/**
 * @author diego
 * 
 */
@Entity
@Table(name = "D_USER")
@NamedQuery(name = "UserBean.all", query = "SELECT A FROM UserBean A")
public class UserBean implements Serializable {

	@Id
	@Column(name = "user_id")
	private Long userID;
	private String gender;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@OneToMany(targetEntity = UserBean.class, cascade = CascadeType.ALL)
	private List<UserBean> friendUserList;

	UserBean() {
	}

	public UserBean(Long userID, String gender) {
		super();
		this.userID = userID;
		this.gender = gender;
		this.creationDate = new Date();
		friendUserList = new ArrayList<UserBean>();
	}

	public Long getUserID() {
		return userID;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public List<UserBean> getFriendUserList() {
		return friendUserList;
	}
	

	public String getGender() {
		return gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		UserBean other = (UserBean) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

}
