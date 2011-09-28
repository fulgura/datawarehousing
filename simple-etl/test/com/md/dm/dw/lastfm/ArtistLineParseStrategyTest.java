/**
 * 
 */
package com.md.dm.dw.lastfm;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.md.dm.dw.lastfm.model.Artist;

/**
 * @author diego
 * 
 */
public class ArtistLineParseStrategyTest {

	private final String completeArtistLine = "120	Deru	http://www.last.fm/music/Deru	http://userserve-ak.last.fm/serve/252/27329185.jpg";
	private final String incompleteArtistLine = "133	Lauki	";
	private final String invalidArtistLine = "120";
	private ArtistLineParseStrategy artistCreatorStrategy;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		artistCreatorStrategy = new ArtistLineParseStrategy();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testCreateWithCompleteLine() throws Exception {
		Artist artist = artistCreatorStrategy.create(completeArtistLine);
		Assert.assertNotNull(artist);
		Assert.assertEquals(new Long(120), artist.getLastfmId());
		Assert.assertEquals("Deru", artist.getName());
		Assert.assertEquals("http://www.last.fm/music/Deru", artist.getUrl());
		Assert.assertEquals(
				"http://userserve-ak.last.fm/serve/252/27329185.jpg",
				artist.getPictureURL());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testCreateWithIncompleteLine() throws Exception {
		Artist artist = artistCreatorStrategy.create(incompleteArtistLine);
		Assert.assertNotNull(artist);
		Assert.assertEquals(new Long(133), artist.getLastfmId());
		Assert.assertEquals("Lauki", artist.getName());
		Assert.assertNull(artist.getUrl());
		Assert.assertNull(artist.getPictureURL());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public final void testCanNotCreateWithInvalidLine() throws Exception {
		Artist artist = artistCreatorStrategy.create(invalidArtistLine);
		Assert.assertNotNull(artist);
		Assert.assertEquals(new Long(133), artist.getLastfmId());
		Assert.assertEquals("Lauki", artist.getName());
		Assert.assertNull(artist.getUrl());
		Assert.assertNull(artist.getPictureURL());
	}

}
