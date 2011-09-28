/**
 * 
 */
package com.md.dm.dw.lastfm;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.md.dm.dw.lastfm.model.Tag;

/**
 * @author diego
 * 
 */
public class TagLineParseStrategyTest {

	private String tagLine;
	private TagLineParseStrategy creatorStrategy;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tagLine = "1	metal";
		creatorStrategy = new TagLineParseStrategy();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreateOne() throws Exception {
		Tag tag = creatorStrategy.create(tagLine);
		Assert.assertNotNull(tag);
		Assert.assertEquals(new Long(1), tag.getTagID());
		Assert.assertEquals("metal", tag.getTagValue());
	}

}
