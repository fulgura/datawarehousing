/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.md.dm.dw.lastfm.model.Artist;

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
	 * Test method for {@link com.md.dm.dw.lastfm.ArtistCSVReader#getFilename()}
	 * .
	 */
	@Test
	public final void testGetFilename() throws Exception {
		ArtistCSVReader reader = new ArtistCSVReader(filename);
		Assert.assertEquals(filename, reader.getFilename());
	}

	@Test(expected = FileNotFoundException.class)
	public final void testCreateWithNonExixtingFilename() throws Exception {
		new ArtistCSVReader("-----");
	}

	@Test
	public final void testReadArtist() throws Exception {
		ArtistCSVReader reader = new ArtistCSVReader(filename);
		Artist artist = reader.nextArtist();
		Assert.assertNotNull(artist);
	}

	@Test
	public final void testHasMoreArtists() throws Exception {
		ArtistCSVReader reader = new ArtistCSVReader(filename);
		Assert.assertTrue(reader.hasMoreArtist());
	}

}
