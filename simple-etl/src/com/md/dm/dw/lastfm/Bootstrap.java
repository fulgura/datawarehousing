/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.openejb.api.LocalClient;

import com.md.dm.dw.lastfm.model.ArtistBean;
import com.md.dm.dw.lastfm.service.ArtistBeanService;

import de.umass.lastfm.Artist;

/**
 * @author diego
 * 
 */
@LocalClient
public class Bootstrap {

	private String key = "3cd3f363864345e489dc98b3c2eb64b0"; // api key
	private String secret = "0c32723a33b58a523da492312a03b311"; // api secret
	private String user = "a_e_r_e_a"; // user name
	private String password = "42067062"; // user's password
	private Connector connector = null;

	@EJB
	private ArtistBeanService artistService;
	private InstanceCreator<ArtistBean> instanceCreator;

	public Bootstrap() throws Exception {
		instanceCreator = new InstanceCreator<ArtistBean>("lastfm/artists.dat",
				new ArtistLineParseStrategy());
		connector = new Connector(user, password, key, secret);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new Bootstrap().run();

	}

	private void run() throws Exception {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.LocalInitialContextFactory");
		p.put("lastfmDatabase", "new://Resource?type=DataSource");
		p.put("lastfmDatabase.JdbcDriver", "org.postgresql.Driver");
		p.put("lastfmDatabase.JdbcUrl", "jdbc:postgresql://localhost/lastfm");
		p.put("lastfmDatabase.UserName", "dw");
		p.put("lastfmDatabase.Password", "dw");

		InitialContext initialContext = new InitialContext(p);
		initialContext.bind("inject", this);

		// while(instanceCreator.hasMoreArtist()){
		// ArtistBean artist =
		// artistService.create(instanceCreator.nextInstance());
		// System.out.println(artist);
		// }
		ArtistBean artist = instanceCreator.nextInstance();
		Artist artistInfo = connector.artistInfo(artist.getName());
		System.out.println(artistInfo);
		artist = artistService.create(artist);

	}

}
