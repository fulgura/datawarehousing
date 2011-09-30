/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.md.dm.dw.lastfm.model.Artist;

/**
 * @author diego
 * 
 */
@Stateless
public class ArtistRemoteService implements ArtistService {

	@PersistenceContext(unitName = "lastfm-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#create(java.lang.Object)
	 */
	@Override
	public Artist create(Artist object) throws Exception {
		entityManager.persist(object);
		return object;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#read(java.lang.Long)
	 */
	@Override
	public Artist read(Long id) throws Exception {
		return entityManager.find(Artist.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#update(java.lang.Object)
	 */
	@Override
	public Artist update(Artist object) throws Exception {
		entityManager.merge(object);
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#delete(java.lang.Object)
	 */
	@Override
	public void delete(Artist object) throws Exception {
		Artist atachedObject = entityManager.find(Artist.class, object.getArtistID());
		entityManager.remove(atachedObject);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Artist> all() throws Exception {
		return entityManager.createNamedQuery("Artist.all").getResultList();
	}

}
