/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.openejb.api.LocalClient;

import com.md.dm.dw.lastfm.entity.ArtistBean;
import com.md.dm.dw.lastfm.entity.TagBean;
import com.md.dm.dw.lastfm.entity.TaggingBean;
import com.md.dm.dw.lastfm.entity.UserBean;
import com.md.dm.dw.lastfm.service.ArtistBeanService;
import com.md.dm.dw.lastfm.service.ListeningBeanService;
import com.md.dm.dw.lastfm.service.TagBeanService;
import com.md.dm.dw.lastfm.service.TaggingBeanService;
import com.md.dm.dw.lastfm.service.UserBeanService;

/**
 * @author diego
 * 
 */
@LocalClient
public class BootstrapTest {

	@EJB
	private ArtistBeanService artistBeanService;
	@EJB
	private TagBeanService tagBeanService;
	@EJB
	private UserBeanService userBeanService;
	@EJB
	private TaggingBeanService taggingBeanService;
	@EJB
	private ListeningBeanService listeningBeanService;

	public BootstrapTest() throws Exception {
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new BootstrapTest().run();

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

		List<UserBean> userList = new ArrayList<UserBean>();
		List<ArtistBean> artistList = new ArrayList<ArtistBean>();
		List<TagBean> tagList = new ArrayList<TagBean>();
		Random randomGenerator = new Random();
		

		for (int i = 0; i < 10; i++) {
			
			UserBean user = new UserBean(new Long(i), ((i % 2) == 0) ? "M"
					: "F", "User_" + i, randomGenerator.nextInt(60) - 18);
			userList.add(userBeanService.create(user));

			ArtistBean artist = new ArtistBean(new Long(i), "Artist_" + i, "",
					"");
			artistList.add(artistBeanService.create(artist));

			TagBean tag = new TagBean(new Long(i), "Tag_" + i);
			tagList.add(tagBeanService.create(tag));

		}
		
		for (int i = 0; i < 10; i++) {
			TaggingBean taggingBean = new TaggingBean(artistList.get(randomGenerator.nextInt(10)), tagList.get(randomGenerator.nextInt(10)), userList.get(randomGenerator.nextInt(10)), new Date());
			taggingBeanService.create(taggingBean);
		}
	}

}
