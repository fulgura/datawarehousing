/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.md.dm.dw.lastfm.model.ArtistBean;

/**
 * @author diego
 * 
 */
@Stateless
public class ArtistBeanRemoteService implements ArtistBeanService {

	@PersistenceContext(unitName = "lastfm-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#create(java.lang.Object)
	 */
	@Override
	public ArtistBean create(ArtistBean object) throws Exception {
		entityManager.persist(object);
		return object;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#read(java.lang.Long)
	 */
	@Override
	public ArtistBean read(Long id) throws Exception {
		return entityManager.find(ArtistBean.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#update(java.lang.Object)
	 */
	@Override
	public ArtistBean update(ArtistBean object) throws Exception {
		entityManager.merge(object);
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#delete(java.lang.Object)
	 */
	@Override
	public void delete(ArtistBean object) throws Exception {
		ArtistBean atachedObject = entityManager.find(ArtistBean.class, object.getArtistID());
		entityManager.remove(atachedObject);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArtistBean> all() throws Exception {
		return entityManager.createNamedQuery("ArtistBean.all").getResultList();
	}

}
