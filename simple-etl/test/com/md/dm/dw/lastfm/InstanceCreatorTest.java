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
public class InstanceCreatorTest {

	private String filename;
	private ArtistLineParseStrategy artistLineParseStrategy;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		filename = "lastfm/artists.dat";
		artistLineParseStrategy = new ArtistLineParseStrategy();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.md.dm.dw.lastfm.InstanceCreator#getFilename()}
	 * .
	 */
	@Test
	public final void testGetFilename() throws Exception {
		InstanceCreator<Artist> reader = new InstanceCreator<Artist>(filename,
				artistLineParseStrategy);
		Assert.assertEquals(filename, reader.getFilename());
	}

	@Test(expected = FileNotFoundException.class)
	public final void testCreateWithNonExixtingFilename() throws Exception {
		InstanceCreator<Artist> reader = new InstanceCreator<Artist>("-----",
				artistLineParseStrategy);
	}

	@Test
	public final void testReadArtist() throws Exception {
		InstanceCreator<Artist> reader = new InstanceCreator<Artist>(filename,
				artistLineParseStrategy);
		Artist artist = reader.nextArtist();
		Assert.assertNotNull(artist);
	}

	@Test
	public final void testReadAllArtist() throws Exception {
		InstanceCreator<Artist> reader = new InstanceCreator<Artist>(filename,
				artistLineParseStrategy);
		while (reader.hasMoreArtist()) {
			Artist artist = reader.nextArtist();
			Assert.assertNotNull(artist);
			System.out.println(artist);
		}
	}

	@Test
	public final void testHasMoreArtists() throws Exception {
		InstanceCreator<Artist> reader = new InstanceCreator<Artist>(filename,
				artistLineParseStrategy);
		Assert.assertTrue(reader.hasMoreArtist());
	}

}
