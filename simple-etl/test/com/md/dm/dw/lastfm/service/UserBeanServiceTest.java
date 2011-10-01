/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import static org.junit.Assert.fail;

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

import com.md.dm.dw.lastfm.InstanceCreator;
import com.md.dm.dw.lastfm.UserLineParseStrategy;
import com.md.dm.dw.lastfm.entity.ArtistBean;
import com.md.dm.dw.lastfm.entity.UserBean;

/**
 * @author diego
 * 
 */
@LocalClient
public class UserBeanServiceTest {

	@EJB
	private UserBeanService userBeanService;
	private InstanceCreator<UserBean> instanceCreator;

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
		initialContext.bind("inject", this);

		instanceCreator = new InstanceCreator<UserBean>(
				"lastfm/user_artists.dat", new UserLineParseStrategy());

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		List<UserBean> artistList = userBeanService.all();
		for (UserBean userBean : artistList) {
			userBeanService.delete(userBean);
		}
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.UserBeanRemoteService#create(com.md.dm.dw.lastfm.entity.UserBean)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testCreate() throws Exception {
		UserBean userBean = instanceCreator.nextInstance();
		userBeanService.create(userBean);
		Assert.assertNotNull(userBean);
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.UserBeanRemoteService#read(java.lang.Long)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testRead() throws Exception {
		UserBean userBean = instanceCreator.nextInstance();
		userBeanService.create(userBean);
		Assert.assertNotNull(userBean);
		UserBean savedUserBean = userBeanService.read(userBean.getUserID());
		Assert.assertEquals(userBean, savedUserBean);
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.UserBeanRemoteService#update(com.md.dm.dw.lastfm.entity.UserBean)}
	 * .
	 */
	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.UserBeanRemoteService#delete(com.md.dm.dw.lastfm.entity.UserBean)}
	 * .
	 */
	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.UserBeanRemoteService#all()}.
	 */
	@Test
	public final void testAll() {
		fail("Not yet implemented"); // TODO
	}

}
