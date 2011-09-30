/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.md.dm.dw.lastfm.model.TagBean;

/**
 * @author diego
 * 
 */
public class TagBeanRemoteService implements TagBeanService {

	@PersistenceContext(unitName = "lastfm-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#create(java.lang.Object)
	 */
	@Override
	public TagBean create(TagBean object) throws Exception {
		entityManager.persist(object);
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#read(java.lang.Long)
	 */
	@Override
	public TagBean read(Long id) throws Exception {
		return entityManager.find(TagBean.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#update(java.lang.Object)
	 */
	@Override
	public TagBean update(TagBean object) throws Exception {
		entityManager.merge(object);
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#delete(java.lang.Object)
	 */
	@Override
	public void delete(TagBean object) throws Exception {
		entityManager.remove(object);
	}

	@Override
	public List<TagBean> all() throws Exception {
		return entityManager.createNamedQuery("TagBean.all").getResultList();
	}

}
