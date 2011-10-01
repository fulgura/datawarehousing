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

import com.md.dm.dw.lastfm.Connector;
import com.md.dm.dw.lastfm.InstanceCreator;
import com.md.dm.dw.lastfm.TagLineParseStrategy;
import com.md.dm.dw.lastfm.model.TagBean;

import de.umass.lastfm.Tag;

/**
 * @author diego
 * 
 */
@LocalClient
public class TagBeanRemoteServiceTest {

	@EJB
	private TagBeanService tagBeanService;

	private String key = "3cd3f363864345e489dc98b3c2eb64b0"; // api key
	private String secret = "0c32723a33b58a523da492312a03b311"; // api secret
	private String user = "a_e_r_e_a"; // user name
	private String password = "42067062"; // user's password
	private Connector connector = null;
	private InstanceCreator<TagBean> instanceCreator;

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
		instanceCreator = new InstanceCreator<TagBean>("lastfm/tags.dat",
				new TagLineParseStrategy());
		connector = new Connector(user, password, key, secret);
		initialContext.bind("inject", this);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		List<TagBean> tagList = tagBeanService.all();
		for (TagBean tag : tagList) {
			tagBeanService.delete(tag);
		}
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.TagBeanRemoteService#create(com.md.dm.dw.lastfm.model.TagBean)}
	 * .
	 */
	@Test
	public final void testCreate() throws Exception {
		TagBean tagBean = instanceCreator.nextInstance();
		TagBean createdTagBean = tagBeanService.create(tagBean);
		Assert.assertNotNull(createdTagBean);
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.TagBeanRemoteService#read(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testRead() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.TagBeanRemoteService#update(com.md.dm.dw.lastfm.model.TagBean)}
	 * .
	 */
	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.TagBeanRemoteService#delete(com.md.dm.dw.lastfm.model.TagBean)}
	 * .
	 */
	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.TagBeanRemoteService#all()}.
	 */
	@Test
	public final void testAll() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void testCreateWithLastfmTagInfo() throws Exception {
		TagBean tagBean = instanceCreator.nextInstance();
		Tag tagInfo = connector.tagInfo(tagBean.getTagValue());
		tagBean.addTagInfo(tagInfo);
		TagBean createdTagBean = tagBeanService.create(tagBean);
		Assert.assertNotNull(createdTagBean);
	}


}
