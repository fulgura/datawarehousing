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

import com.md.dm.dw.lastfm.entity.DateHierarchyBean;

@LocalClient
public class DateHierarchyBeanServiceTest {

	@EJB
	private DateHierarchyBeanService dateHierarchyBeanService;

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

	@After
	public void tearDown() throws Exception {
		List<DateHierarchyBean> all = dateHierarchyBeanService.all();
		for (DateHierarchyBean dateHierarchyBean : all) {
			dateHierarchyBeanService.delete(dateHierarchyBean);
		}
	}

	@Test
	public final void testCreate() throws Exception {
		DateHierarchyBean dateHierarchyBean = new DateHierarchyBean(
				1238536800000l);
		Assert.assertNull(dateHierarchyBean.getId());
		dateHierarchyBean = dateHierarchyBeanService.create(dateHierarchyBean);
		Assert.assertNotNull(dateHierarchyBean.getId());
	}

	@Test
	public final void testRead() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAll() {
		fail("Not yet implemented"); // TODO
	}

}
