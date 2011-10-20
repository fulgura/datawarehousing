/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.md.dm.dw.lastfm.entity.DateHierarchyBean;

/**
 * @author diego
 * 
 */
@Stateless
public class DateHierarchyBeanRemoteService implements DateHierarchyBeanService {

	@PersistenceContext(unitName = "lastfm-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#create(java.lang.Object)
	 */
	@Override
	public DateHierarchyBean create(DateHierarchyBean bean) throws Exception {
		entityManager.persist(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#read(java.lang.Long)
	 */
	@Override
	public DateHierarchyBean read(Long id) throws Exception {
		return entityManager.find(DateHierarchyBean.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#update(java.lang.Object)
	 */
	@Override
	public DateHierarchyBean update(DateHierarchyBean bean) throws Exception {
		entityManager.merge(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#delete(java.lang.Object)
	 */
	@Override
	public void delete(DateHierarchyBean bean) throws Exception {
		DateHierarchyBean atachedObject = entityManager.find(
				DateHierarchyBean.class, bean.getId());
		entityManager.remove(atachedObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#all()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DateHierarchyBean> all() throws Exception {
		return entityManager.createNamedQuery("DateHierarchyBean.all")
				.getResultList();
	}

}
