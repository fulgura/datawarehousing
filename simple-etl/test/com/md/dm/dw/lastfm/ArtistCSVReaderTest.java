/**
 * 
 */
package com.md.dm.dw.lastfm;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author diego
 *
 */
public class ArtistCSVReaderTest {

	private final String filename = "lastfm/artists.dat";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.md.dm.dw.lastfm.ArtistCSVReader#getFilename()}.
	 */
	@Test
	public final void testGetFilename() {
		ArtistCSVReader reader = new ArtistCSVReader(filename);
		Assert.assertEquals(filename, reader.getFilename());
	}

	/**
	 * Test method for {@link com.md.dm.dw.lastfm.ArtistCSVReader#toString()}.
	 */
	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
