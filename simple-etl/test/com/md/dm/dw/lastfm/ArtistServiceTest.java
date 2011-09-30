/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.Assert;

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
		p.put("lastfmDatabase", "new://Resource?type=DataSource");
		p.put("lastfmDatabase.JdbcDriver", "org.postgresql.Driver");
		p.put("lastfmDatabase.JdbcUrl", "jdbc:postgresql://localhost/lastfm");
		p.put("lastfmDatabase.UserName", "dw");
		p.put("lastfmDatabase.Password", "dw");

		InitialContext initialContext = new InitialContext(p);
		instanceCreator = new InstanceCreator<Artist>("lastfm/artists.dat",
				new ArtistLineParseStrategy());
		initialContext.bind("inject", this);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		List<Artist> artistList = artistService.all();
		for (Artist artist : artistList) {
			artistService.delete(artist);
		}
	}

	@Test
	public final void testCreateArtist() throws Exception {
		Artist artist = instanceCreator.nextInstance();
		artist = artistService.create(artist);
		Assert.assertNotNull(artist.getArtistID());
	}

	@Test
	public final void testFindArtist() throws Exception {
		Artist artist = instanceCreator.nextInstance();
		artist = artistService.create(artist);
		Assert.assertNotNull(artist.getArtistID());
		Artist artistFounded = artistService.read(artist.getArtistID());
		Assert.assertEquals(artist, artistFounded);
	}

	@Test
	public final void testReadArtist() throws Exception {
		Artist artist = instanceCreator.nextInstance();
		artist = artistService.create(artist);
		Assert.assertNotNull(artist.getArtistID());
		Artist sameArtist = artistService.read(artist.getArtistID());
		Assert.assertNotNull(sameArtist.getArtistID());

	}

}
