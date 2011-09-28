/**
 * 
 */
package com.md.dm.dw.lastfm;

/**
 * 
 * @author diego
 *
 */
public interface InstanceCreatorStrategy<E> {

	public E create(final String line) throws Exception;
}
