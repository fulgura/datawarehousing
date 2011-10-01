/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.util.Scanner;

import com.md.dm.dw.lastfm.entity.ArtistBean;

/**
 * Crates an instance of {@link ArtistBean} implementing a Strategy pattern. For
 * more details see {@link LineParseStrategy}
 * 
 * @author diego
 * 
 */
public class ArtistLineParseStrategy implements LineParseStrategy<ArtistBean> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.IntanceCreatorStrategy#create(java.lang.String)
	 */
	@Override
	public ArtistBean create(String line) throws Exception {
		Long lastfmId;
		String name;
		String url;
		String imageURL;
		try {
			Scanner lineScanner = new Scanner(line);
			lastfmId = lineScanner.nextLong();
			name = lineScanner.next();
			url = null;
			imageURL = null;
			if (lineScanner.hasNext()) {
				url = lineScanner.next();
			}
			if (lineScanner.hasNext()) {
				imageURL = lineScanner.next();
			}
		} catch (Exception e) {
			throw new Exception("Can not create an ArtistBean with this line: "
					+ line + ". Why? because " + e.getMessage());
		}
		return new ArtistBean(lastfmId, name, url, imageURL);
	}

}
