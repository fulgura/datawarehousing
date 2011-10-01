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
import com.md.dm.dw.lastfm.model.TagBean;
import com.md.dm.dw.lastfm.model.UserBean;
import com.md.dm.dw.lastfm.service.ArtistBeanService;
import com.md.dm.dw.lastfm.service.TagBeanService;
import com.md.dm.dw.lastfm.service.UserBeanService;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Tag;

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
	private ArtistBeanService artistBeanService;
	@EJB
	private TagBeanService tagBeanService;
	@EJB
	private UserBeanService userBeanService;

	private InstanceCreator<ArtistBean> artistBeanCreator;
	private InstanceCreator<TagBean> tagBeanCreator;
	private InstanceCreator<UserBean> userBeanCreator;

	public Bootstrap() throws Exception {
		artistBeanCreator = new InstanceCreator<ArtistBean>(
				"lastfm/artists.dat", new ArtistLineParseStrategy());
		tagBeanCreator = new InstanceCreator<TagBean>("lastfm/tags.dat",
				new TagLineParseStrategy());
		userBeanCreator = new InstanceCreator<UserBean>(
				"lastfm/user_artists.dat", new UserLineParseStrategy());
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

		this.test();
		// this.createAllArtist();
		// this.createAllTags();
		this.createAllUsers();
	}

	private void test() throws Exception {

		TagBean tagBean = tagBeanService.create(tagBeanCreator.nextInstance());
		ArtistBean artistBean = artistBeanService.create(artistBeanCreator
				.nextInstance());
		UserBean userBean = userBeanService.create(userBeanCreator
				.nextInstance());
		
		// TaggingBean taggingBean = new TaggingBean(artistBean, tagBean,
		// userBean, new Date());

	}

	private void createAllUsers() {

	}

	private void createAllTags() throws Exception {
		while (tagBeanCreator.hasMoreArtist()) {
			TagBean tagBean = tagBeanCreator.nextInstance();

			try {
				Tag tagInfo = connector.tagInfo(tagBean.getTagValue());
				tagBean.addTagInfo(tagInfo);
			} catch (Exception e) {
				System.err.println("Couldn't add info for tag: "
						+ tagBean.getTagValue());
			}
			tagBean = tagBeanService.create(tagBean);
		}

	}

	private void createAllArtist() throws Exception {
		while (artistBeanCreator.hasMoreArtist()) {
			ArtistBean artistBean = artistBeanCreator.nextInstance();
			Artist artistInfo = connector.artistInfo(artistBean.getName());
			artistBean.addArtistInfo(artistInfo);
			artistBeanService.create(artistBean);
		}
	}

}
