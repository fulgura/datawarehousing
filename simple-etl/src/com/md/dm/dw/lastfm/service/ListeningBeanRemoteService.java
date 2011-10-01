/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.md.dm.dw.lastfm.entity.ListeningBean;

/**
 * @author diego
 * 
 */
@Stateless
public class ListeningBeanRemoteService implements ListeningBeanService {

	@PersistenceContext(unitName = "lastfm-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#create(java.lang.Object)
	 */
	@Override
	public ListeningBean create(ListeningBean bean) throws Exception {
		entityManager.persist(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#read(java.lang.Long)
	 */
	@Override
	public ListeningBean read(Long id) throws Exception {
		return entityManager.find(ListeningBean.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#update(java.lang.Object)
	 */
	@Override
	public ListeningBean update(ListeningBean bean) throws Exception {
		entityManager.merge(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#delete(java.lang.Object)
	 */
	@Override
	public void delete(ListeningBean bean) throws Exception {
		ListeningBean atachedObject = entityManager.find(ListeningBean.class,
				bean.getListeningId());
		entityManager.remove(atachedObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#all()
	 */
	@Override
	public List<ListeningBean> all() throws Exception {
		return entityManager.createNamedQuery("ListeningBean.all")
				.getResultList();
	}

}
