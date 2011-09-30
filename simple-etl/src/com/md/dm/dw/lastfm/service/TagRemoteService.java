/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.md.dm.dw.lastfm.model.Tag;

/**
 * @author diego
 * 
 */
public class TagRemoteService implements TagService {

	@PersistenceContext(unitName = "lastfm-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#create(java.lang.Object)
	 */
	@Override
	public Tag create(Tag object) throws Exception {
		entityManager.persist(object);
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#read(java.lang.Long)
	 */
	@Override
	public Tag read(Long id) throws Exception {
		return entityManager.find(Tag.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#update(java.lang.Object)
	 */
	@Override
	public Tag update(Tag object) throws Exception {
		entityManager.merge(object);
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#delete(java.lang.Object)
	 */
	@Override
	public void delete(Tag object) throws Exception {
		entityManager.remove(object);
	}

	@Override
	public List<Tag> all() throws Exception {
		return entityManager.createNamedQuery("Tag.all").getResultList();
	}

}
