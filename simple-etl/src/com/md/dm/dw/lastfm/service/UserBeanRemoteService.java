/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.md.dm.dw.lastfm.model.UserBean;

/**
 * @author diego
 * 
 */
@Stateless
public class UserBeanRemoteService implements UserBeanService {

	@PersistenceContext(unitName = "lastfm-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#create(java.lang.Object)
	 */
	@Override
	public UserBean create(UserBean bean) throws Exception {
		entityManager.persist(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#read(java.lang.Long)
	 */
	@Override
	public UserBean read(Long id) throws Exception {
		return entityManager.find(UserBean.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#update(java.lang.Object)
	 */
	@Override
	public UserBean update(UserBean bean) throws Exception {
		entityManager.merge(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#delete(java.lang.Object)
	 */
	@Override
	public void delete(UserBean bean) throws Exception {
		UserBean atachedObject = entityManager.find(UserBean.class,
				bean.getUserID());
		entityManager.remove(atachedObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#all()
	 */
	@Override
	public List<UserBean> all() throws Exception {
		return entityManager.createNamedQuery("UserBean.all").getResultList();
	}

}
