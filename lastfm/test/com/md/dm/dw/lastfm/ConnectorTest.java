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
		Collection<Artist> similarArtistCollection = connector.similarArtists("Metallica");
		Assert.assertFalse(similarArtistCollection.isEmpty());
	}
}
