package com.md.dm.dw.lastfm.entity;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateHierarchyBeanTest {

	DateHierarchyBean dateHierarchyBean;

	@Before
	public void setUp() throws Exception {
		this.dateHierarchyBean = new DateHierarchyBean(1238536800000l);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetDate() {
		Assert.assertEquals(new Date(1238536800000l),
				dateHierarchyBean.getDate());
	}

	@Test
	public final void testGetTime() {
		Assert.assertEquals(new Date(1238536800000l),
				dateHierarchyBean.getTime());
	}

	@Test
	public final void testGetDateAndTime() {
		Assert.assertEquals(new Date(1238536800000l),
				dateHierarchyBean.getDateAndTime());
	}

	@Test
	public final void testGetCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(1238536800000l);
		Assert.assertEquals(calendar, dateHierarchyBean.getCalendar());
	}

	@Test
	public final void testGetYear() {
		Assert.assertEquals(2009, dateHierarchyBean.getYear());
	}

	@Test
	public final void testGetMonth() {
		Assert.assertEquals(2, dateHierarchyBean.getMonth());
	}

	@Test
	public final void testGetQuarter() {
		Assert.assertEquals(1, dateHierarchyBean.getQuarter());
	}

	@Test
	public final void testGetWeekOfMonth() {
		Assert.assertEquals(5, dateHierarchyBean.getWeekOfMonth());
	}

	@Test
	public final void testGetDayOfMonth() {
		Assert.assertEquals(31, dateHierarchyBean.getDayOfMonth());
	}

}
