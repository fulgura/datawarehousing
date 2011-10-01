/**
 * 
 */
package com.md.dm.dw.lastfm.service;

import static org.junit.Assert.fail;

import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.openejb.api.LocalClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.md.dm.dw.lastfm.InstanceCreator;
import com.md.dm.dw.lastfm.LineParseStrategy;
import com.md.dm.dw.lastfm.model.ListeningBean;

/**
 * @author diego
 * 
 */
@LocalClient
public class ListeningBeanServiceTest {

	@EJB
	private ListeningBeanService listeningBeanService;
	private InstanceCreator<ListeningBean> instanceCreator;

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
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.CRUDService#create(java.lang.Object)}.
	 */
	@Test
	public final void testCreate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.CRUDService#read(java.lang.Long)}.
	 */
	@Test
	public final void testRead() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.CRUDService#update(java.lang.Object)}.
	 */
	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.md.dm.dw.lastfm.service.CRUDService#delete(java.lang.Object)}.
	 */
	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.md.dm.dw.lastfm.service.CRUDService#all()}.
	 */
	@Test
	public final void testAll() {
		fail("Not yet implemented"); // TODO
	}

}
