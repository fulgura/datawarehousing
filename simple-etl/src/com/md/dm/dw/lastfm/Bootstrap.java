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
import com.md.dm.dw.lastfm.service.ArtistService;

/**
 * @author diego
 *
 */
@LocalClient
public class Bootstrap {

	@EJB
	private ArtistService artistService;
	private InstanceCreator<ArtistBean> instanceCreator;

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
		instanceCreator = new InstanceCreator<ArtistBean>("lastfm/artists.dat",
				new ArtistLineParseStrategy());
		initialContext.bind("inject", this);
		
//		while(instanceCreator.hasMoreArtist()){
//			ArtistBean artist = artistService.create(instanceCreator.nextInstance());
//			System.out.println(artist);
//		}
		ArtistBean artist = instanceCreator.nextInstance();
		artist = artistService.create(artist);

		
	}

}
