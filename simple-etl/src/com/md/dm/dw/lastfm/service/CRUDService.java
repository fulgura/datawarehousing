package com.md.dm.dw.lastfm.service;

import java.util.List;

/**
 * Defining CRUD operation that each service must support.
 * 
 * @author diego
 * 
 * @param <E>
 */
public interface CRUDService<E> {

	public abstract E create(E object) throws Exception;

	public abstract E read(Long id) throws Exception;

	public abstract E update(E object) throws Exception;

	public abstract void delete(E object) throws Exception;

	public List<E> all() throws Exception;

}