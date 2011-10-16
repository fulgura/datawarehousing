/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.md.dm.dw.lastfm.entity.TaggingBean;

/**
 * @author diego
 * 
 */
@Stateless
public class TaggingBeanRemoteService implements TaggingBeanService {

	@PersistenceContext(unitName = "lastfm-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#create(java.lang.Object)
	 */
	@Override
	public TaggingBean create(TaggingBean bean) throws Exception {
		entityManager.persist(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#read(java.lang.Long)
	 */
	@Override
	public TaggingBean read(Long id) throws Exception {
		return entityManager.find(TaggingBean.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#update(java.lang.Object)
	 */
	@Override
	public TaggingBean update(TaggingBean bean) throws Exception {
		entityManager.merge(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#delete(java.lang.Object)
	 */
	@Override
	public void delete(TaggingBean bean) throws Exception {
//		TaggingBean atachedObject = entityManager.find(TaggingBean.class,
//				bean.getTaggingId());
//		entityManager.remove(atachedObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.service.CRUDService#all()
	 */
	@Override
	public List<TaggingBean> all() throws Exception {
		return entityManager.createNamedQuery("TaggingBean.all")
				.getResultList();
	}

}
