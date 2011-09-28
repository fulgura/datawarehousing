/**
 * 
 */
package com.md.dm.dw.lastfm;

import static org.junit.Assert.fail;

import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.openejb.api.LocalClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.md.dm.dw.lastfm.model.Artist;
import com.md.dm.dw.lastfm.service.ArtistService;

/**
 * @author diego
 * 
 */
@LocalClient
public class ArtistServiceTest {

	@EJB
	private ArtistService artistService;
	private InstanceCreator<Artist> instanceCreator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.LocalInitialContextFactory");
		p.put("movieDatabase", "new://Resource?type=DataSource");
		p.put("movieDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("movieDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");


		InitialContext initialContext = new InitialContext(p);
		instanceCreator = new InstanceCreator<Artist>(
				"lastfm/artists.dat", new ArtistLineParseStrategy());;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreateArtist() throws Exception {
		Artist artist = instanceCreator.nextInstance();
		artistService.create(artist);
	}

}
