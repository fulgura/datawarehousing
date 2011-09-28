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
public class ArtistCreatorStrategyTest {

	private final String completeArtistLine = "120	Deru	http://www.last.fm/music/Deru	http://userserve-ak.last.fm/serve/252/27329185.jpg";
	private final String incompleteArtistLine = "133	Lauki	http://www.last.fm/music/Lauki	";
	private final String invalidArtistLine = "120";
	private ArtistCreatorStrategy artistCreatorStrategy;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		artistCreatorStrategy = new ArtistCreatorStrategy();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.ArtistCreatorStrategy#create(java.lang.String)}
	 * .
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

}
