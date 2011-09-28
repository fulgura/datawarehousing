/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.util.Scanner;

import com.md.dm.dw.lastfm.model.Artist;

/**
 * Crates an instance of {@link Artist} implementing a Startegy pattern. For
 * more details see {@link InstanceCreatorStrategy}
 * 
 * @author diego
 * 
 */
public class ArtistCreatorStrategy implements InstanceCreatorStrategy<Artist> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.IntanceCreatorStrategy#create(java.lang.String)
	 */
	@Override
	public Artist create(String line) throws Exception {
		Scanner lineScanner = new Scanner(line);
		Long lastfmId = lineScanner.nextLong();
		String name = lineScanner.next();
		String url = null;
		String imageURL = null;
		if (lineScanner.hasNext()) {
			url = lineScanner.next();
		}
		if (lineScanner.hasNext()) {
			imageURL = lineScanner.next();
		}
		return new Artist(lastfmId, name, url, imageURL);
	}

}
