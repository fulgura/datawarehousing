package com.md.dm.dw.lastfm.service;

/**
 * Defining CRUD operation that each service must support.
 * 
 * @author diego
 * 
 * @param <E> 
 */
public interface CRUDService<E> {

	public abstract void create(E object) throws Exception;

	public abstract void read(Long id) throws Exception;

	public abstract void update(E object) throws Exception;

	public abstract void delete(E object) throws Exception;

}