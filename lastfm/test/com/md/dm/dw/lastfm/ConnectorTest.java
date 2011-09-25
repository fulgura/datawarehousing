/**
 * 
 */
package com.md.dm.dw.lastfm;

import static org.junit.Assert.*;

import java.util.Collection;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Tag;

/**
 * This is a test case to show how to manage last fm API connections.
 *
 *
 *
 * @author diego
 *
 */
public class ConnectorTest {

	private String key = "3cd3f363864345e489dc98b3c2eb64b0";      // api key
	private String secret = "0c32723a33b58a523da492312a03b311";   // api secret
	private String user = "a_e_r_e_a";     // user name
	private String password = "42067062"; // user's password
	private Connector connector = null;
	private final String artistName = "Metallica";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		connector  = new Connector(user, password, key, secret); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSessionCreated() throws Exception {
		Assert.assertNotNull(connector.getSession());
	}
	
	@Test
	public void testFindRelatedArtistsByName() throws Exception {
		Collection<Artist> similarArtistCollection = connector.similarArtists(artistName);
		Assert.assertFalse(similarArtistCollection.isEmpty());
	}
	
	@Test
	public void testGetArtistInfoByArtistName() throws Exception {
		Artist metallica = connector.artistInfo(artistName);
		Assert.assertNotNull(metallica);
		Assert.assertEquals("65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab", metallica.getMbid());
	}

	@Test
	public void testGetArtistInfoByArtist() throws Exception {
		Artist metallica = connector.artistInfo(artistName);
		Artist metallicaAgain = connector.artistInfo(metallica);
		Assert.assertEquals(metallicaAgain.getMbid(), metallica.getMbid());
	}

	@Test
	public void testGetTagsForArtist() throws Exception {
		Collection<String> tags = connector.tagsNameForArtist(artistName);
		Assert.assertFalse(tags.isEmpty());
	}

	@Test
	public void testGetTopTagsForArtist() throws Exception {
		Collection<Tag> tags = connector.topTagsForArtist(artistName);
		Assert.assertFalse(tags.isEmpty());
	}
}
