/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.openejb.api.LocalClient;

import com.md.dm.dw.lastfm.entity.ArtistBean;
import com.md.dm.dw.lastfm.entity.ListeningBean;
import com.md.dm.dw.lastfm.entity.TagBean;
import com.md.dm.dw.lastfm.entity.TaggingBean;
import com.md.dm.dw.lastfm.entity.UserBean;
import com.md.dm.dw.lastfm.service.ArtistBeanService;
import com.md.dm.dw.lastfm.service.ListeningBeanService;
import com.md.dm.dw.lastfm.service.TagBeanService;
import com.md.dm.dw.lastfm.service.TaggingBeanService;
import com.md.dm.dw.lastfm.service.UserBeanService;
import com.md.dm.dw.lastfm.valueobject.UserArtistWeight;
import com.md.dm.dw.lastfm.valueobject.UserFriend;
import com.md.dm.dw.lastfm.valueobject.UserTaggedArtist;
import com.md.dm.dw.lastfm.valueobject.UserTaggedArtistTimestamp;

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
	@EJB
	private TaggingBeanService taggingBeanService;
	@EJB
	private ListeningBeanService listeningBeanService;

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

		this.populateArtist();
		this.populateUsers();
		this.populateTags();
		this.populateTagging();

	}

	private void test() throws Exception {

		TagBean tagBean = tagBeanService.create(tagBeanCreator.nextInstance());
		ArtistBean artistBean = artistBeanService.create(artistBeanCreator
				.nextInstance());
		UserBean userBean = userBeanService.create(userBeanCreator
				.nextInstance());

		TaggingBean taggingBean = taggingBeanService.create(new TaggingBean(
				artistBean, tagBean, userBean, new Date()));

		System.out.println(taggingBean);
	}

	private void populateTagging() throws Exception {

		int instances = 0;

		while (userTaggedArtistTimestampCreator.hasMoreInstances() && instances < 3000) {
			instances++;
			UserTaggedArtistTimestamp userTaggedArtistTimestamp = userTaggedArtistTimestampCreator
					.nextInstance();

			UserBean userBean = userBeanService.read(userTaggedArtistTimestamp
					.getUserID());
			ArtistBean artistBean = artistBeanService
					.read(userTaggedArtistTimestamp.getArtistID());
			TagBean tagBean = tagBeanService.read(userTaggedArtistTimestamp
					.getTagID());
			if (userBean != null && artistBean != null && tagBean != null) {
				TaggingBean taggingBean = taggingBeanService
						.create(new TaggingBean(artistBean, tagBean, userBean,
								userTaggedArtistTimestamp.getTimestamp()));
				System.out.println(taggingBean);
			} else {
				System.err.println("Couldn't persist: "
						+ userTaggedArtistTimestamp);
			}

		}
	}

	private void populateUsers() throws Exception {
		int instances = 0;

		while (userFriendCreator.hasMoreInstances() && instances < 1000) {
			instances++;
			UserFriend userFriend = userFriendCreator.nextInstance();
			UserBean userBean = userBeanService.update(new UserBean(userFriend
					.getUserid()));

			System.out.println(userBean);
		}
	}

	private void populateTags() throws Exception {
		FileInputStream fstream = new FileInputStream("lastfm/tags.dat");
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;

		int instances = 0;

		// Read File Line By Line
		while ((strLine = br.readLine()) != null && (instances < 1000)) {
			instances++;
			// Print the content on the console
			Scanner lineScanner = new Scanner(strLine);
			TagBean tagBean = new TagBean(lineScanner.nextLong(),
					lineScanner.next());
			try {
				// Tag tagInfo = connector.tagInfo(tagBean.getTagValue());
				// tagBean.addTagInfo(tagInfo);
			} catch (Exception e) {
				System.err.println("Couldn't add info for tag: "
						+ tagBean.getTagValue());
			}
			tagBean = tagBeanService.create(tagBean);
			System.out.println(tagBean);

		}
		// Close the input stream
		in.close();
	}

	/**
	 * Populate all artis taked from lastfm data files.
	 * 
	 * @throws Exception
	 */
	private void populateArtist() throws Exception {
		int instances = 0;

		while (artistBeanCreator.hasMoreInstances() && instances < 1000) {
			instances++;
			ArtistBean artistBean = artistBeanCreator.nextInstance();
			// Artist artistInfo = connector.artistInfo(artistBean.getName());
			// artistBean.addArtistInfo(artistInfo);
			artistBean = artistBeanService.create(artistBean);
			System.out.println(artistBean);
		}
	}

	private final InstanceCreator<UserArtistWeight> userArtistWeightCreator = new InstanceCreator<UserArtistWeight>(
			"lastfm/user_artists.dat",
			new LineParseStrategy<UserArtistWeight>() {

				@Override
				public UserArtistWeight create(String line) throws Exception {
					try {
						Scanner lineScanner = new Scanner(line);
						return new UserArtistWeight(lineScanner.nextLong(),
								lineScanner.nextLong(), lineScanner.nextInt());
					} catch (Exception e) {
						throw new Exception(
								"Can not create an ArtistBean with this line: "
										+ line + ". Why? because "
										+ e.getMessage());
					}
				}
			});

	private final InstanceCreator<UserFriend> userFriendCreator = new InstanceCreator<UserFriend>(
			"lastfm/user_friends.dat", new LineParseStrategy<UserFriend>() {
				@Override
				public UserFriend create(String line) throws Exception {
					try {
						Scanner lineScanner = new Scanner(line);
						return new UserFriend(lineScanner.nextLong(),
								lineScanner.nextLong());
					} catch (Exception e) {
						throw new Exception(
								"Can not create an UserFriend with this line: "
										+ line + ". Why? because "
										+ e.getMessage());
					}
				}
			});

	private final InstanceCreator<UserTaggedArtist> userTaggedArtistCreator = new InstanceCreator<UserTaggedArtist>(
			"lastfm/user_taggedartists.dat",
			new LineParseStrategy<UserTaggedArtist>() {
				@Override
				public UserTaggedArtist create(String line) throws Exception {
					try {
						Scanner lineScanner = new Scanner(line);
						return new UserTaggedArtist(lineScanner.nextLong(),
								lineScanner.nextLong(), lineScanner.nextLong(),
								lineScanner.nextInt(), lineScanner.nextInt(),
								lineScanner.nextInt());
					} catch (Exception e) {
						throw new Exception(
								"Can not create an UserTaggedArtist with this line: "
										+ line + ". Why? because "
										+ e.getMessage());
					}
				}
			});

	private final InstanceCreator<UserTaggedArtistTimestamp> userTaggedArtistTimestampCreator = new InstanceCreator<UserTaggedArtistTimestamp>(
			"lastfm/user_taggedartists-timestamps.dat",
			new LineParseStrategy<UserTaggedArtistTimestamp>() {
				@Override
				public UserTaggedArtistTimestamp create(String line)
						throws Exception {
					try {
						Scanner lineScanner = new Scanner(line);
						return new UserTaggedArtistTimestamp(
								lineScanner.nextLong(), lineScanner.nextLong(),
								lineScanner.nextLong(), new Date(
										lineScanner.nextLong()));
					} catch (Exception e) {
						throw new Exception(
								"Can not create an UserTaggedArtistTimestamp with this line: "
										+ line + ". Why? because "
										+ e.getMessage());
					}
				}
			});
}
