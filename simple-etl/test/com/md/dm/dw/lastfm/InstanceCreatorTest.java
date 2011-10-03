/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.md.dm.dw.lastfm.entity.ArtistBean;
import com.md.dm.dw.lastfm.entity.TagBean;
import com.md.dm.dw.lastfm.entity.UserBean;

/**
 * @author diego
 * 
 */
public class InstanceCreatorTest {

	private String artistFilename;
	private String tagFilename;
	private String userFilename;
	private ArtistLineParseStrategy artistLineParseStrategy;
	private TagLineParseStrategy tagLineParseStrategy;
	private UserLineParseStrategy userLineParseStrategy;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		artistFilename = "lastfm/artists.dat";
		tagFilename = "lastfm/tags.dat";
		userFilename = "lastfm/user_artists.dat";
		artistLineParseStrategy = new ArtistLineParseStrategy();
		tagLineParseStrategy = new TagLineParseStrategy();
		userLineParseStrategy = new UserLineParseStrategy();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.md.dm.dw.lastfm.InstanceCreator#getFilename()}
	 * .
	 */
	@Test
	public final void testGetFilename() throws Exception {
		InstanceCreator<ArtistBean> reader = new InstanceCreator<ArtistBean>(
				artistFilename, artistLineParseStrategy);
		Assert.assertEquals(artistFilename, reader.getFilename());
	}

	@Test(expected = FileNotFoundException.class)
	public final void testCreateWithNonExixtingFilename() throws Exception {
		InstanceCreator<ArtistBean> reader = new InstanceCreator<ArtistBean>(
				"-----", artistLineParseStrategy);
	}

	@Test
	public final void testReadArtist() throws Exception {
		InstanceCreator<ArtistBean> reader = new InstanceCreator<ArtistBean>(
				artistFilename, artistLineParseStrategy);
		ArtistBean artist = reader.nextInstance();
		Assert.assertNotNull(artist);
	}

	@Test
	public final void testReadAllArtist() throws Exception {
		InstanceCreator<ArtistBean> reader = new InstanceCreator<ArtistBean>(
				artistFilename, artistLineParseStrategy);
		while (reader.hasMoreInstances()) {
			ArtistBean artist = reader.nextInstance();
			Assert.assertNotNull(artist);
			System.out.println(artist);
		}
	}

	@Test
	public final void testHasMoreArtists() throws Exception {
		InstanceCreator<ArtistBean> reader = new InstanceCreator<ArtistBean>(
				artistFilename, artistLineParseStrategy);
		Assert.assertTrue(reader.hasMoreInstances());
	}

	@Test
	public final void testReadTag() throws Exception {
		InstanceCreator<TagBean> reader = new InstanceCreator<TagBean>(
				tagFilename, tagLineParseStrategy);
		TagBean tag = reader.nextInstance();
		Assert.assertNotNull(tag);
	}

	@Test
	public final void testReadAllTags() throws Exception {
		InstanceCreator<TagBean> reader = new InstanceCreator<TagBean>(
				tagFilename, tagLineParseStrategy);
		while (reader.hasMoreInstances()) {
			TagBean tag = reader.nextInstance();
			Assert.assertNotNull(tag);
			System.out.println(tag);
		}
	}

	@Test
	public final void testReadAllUsers() throws Exception {
		InstanceCreator<UserBean> reader = new InstanceCreator<UserBean>(
				userFilename, userLineParseStrategy);

		while (reader.hasMoreInstances()) {
			UserBean userBean = reader.nextInstance();
			Assert.assertNotNull(userBean);
			System.out.println(userBean);
		}
	}
}
