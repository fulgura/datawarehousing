/**
 * 
 */
package com.md.dm.dw.lastfm;

/**
 * 
 * @author diego
 *
 */
public interface LineParseStrategy<E> {

	public E create(final String line) throws Exception;
}
