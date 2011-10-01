/**
 * 
 */
package com.md.dm.dw.lastfm.service;

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

import com.md.dm.dw.lastfm.ArtistLineParseStrategy;
import com.md.dm.dw.lastfm.Connector;
import com.md.dm.dw.lastfm.InstanceCreator;
import com.md.dm.dw.lastfm.model.ArtistBean;
import com.md.dm.dw.lastfm.service.ArtistBeanService;

import de.umass.lastfm.Artist;

/**
 * @author diego
 * 
 */
@LocalClient
public class ArtistBeanServiceTest {

	@EJB
	private ArtistBeanService artistService;
	private String key = "3cd3f363864345e489dc98b3c2eb64b0";      // api key
	private String secret = "0c32723a33b58a523da492312a03b311";   // api secret
	private String user = "a_e_r_e_a";     // user name
	private String password = "42067062"; // user's password
	private Connector connector = null;
	private InstanceCreator<ArtistBean> instanceCreator;

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
		instanceCreator = new InstanceCreator<ArtistBean>("lastfm/artists.dat",
				new ArtistLineParseStrategy());
		connector  = new Connector(user, password, key, secret); 
		initialContext.bind("inject", this);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		List<ArtistBean> artistList = artistService.all();
		for (ArtistBean artist : artistList) {
			artistService.delete(artist);
		}
	}

	@Test
	public final void testCreateArtistBean() throws Exception {
		ArtistBean artist = instanceCreator.nextInstance();
		artist = artistService.create(artist);
		Assert.assertNotNull(artist.getArtistID());
	}

	@Test
	public final void testCreateArtistBeanWithLastfmArtistInfo() throws Exception {
		ArtistBean artistBean = instanceCreator.nextInstance();
		Artist artistInfo = connector.artistInfo(artistBean.getName());
		artistBean.addArtistInfo(artistInfo);
		artistBean = artistService.create(artistBean);
		Assert.assertNotNull(artistBean.getArtistID());
	}

	@Test
	public final void testFindArtist() throws Exception {
		ArtistBean artist = instanceCreator.nextInstance();
		artist = artistService.create(artist);
		Assert.assertNotNull(artist.getArtistID());
		ArtistBean artistFounded = artistService.read(artist.getArtistID());
		Assert.assertEquals(artist, artistFounded);
	}

	@Test
	public final void testDeleteArtist() throws Exception {
		ArtistBean artist = instanceCreator.nextInstance();
		artist = artistService.create(artist);
		Assert.assertNotNull(artist.getArtistID());
		artistService.delete(artist);
		ArtistBean artistFounded = artistService.read(artist.getArtistID());
		Assert.assertEquals(null, artistFounded);
	}

	@Test
	public final void testReadArtist() throws Exception {
		ArtistBean artist = instanceCreator.nextInstance();
		artist = artistService.create(artist);
		Assert.assertNotNull(artist.getArtistID());
		ArtistBean sameArtist = artistService.read(artist.getArtistID());
		Assert.assertNotNull(sameArtist.getArtistID());
	}

	@Test
	public final void testReadAllArtist() throws Exception {
		ArtistBean artist = instanceCreator.nextInstance();
		artist = artistService.create(artist);
		Assert.assertNotNull(artist.getArtistID());
		List<ArtistBean> artistList = artistService.all();
		Assert.assertFalse(artistList.isEmpty());
		Assert.assertEquals(1, artistList.size());
		Assert.assertEquals(artist, artistList.get(0));

	}

}
