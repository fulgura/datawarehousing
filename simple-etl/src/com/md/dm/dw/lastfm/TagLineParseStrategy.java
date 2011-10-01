/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.util.Scanner;

import com.md.dm.dw.lastfm.entity.TagBean;

/**
 * Crates an instance of {@link TagBean} implementing a Strategy pattern. For more
 * details see {@link LineParseStrategy}
 * 
 * @author diego
 * 
 */
public class TagLineParseStrategy implements LineParseStrategy<TagBean> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.IntanceCreatorStrategy#create(java.lang.String)
	 */
	@Override
	public TagBean create(String line) throws Exception {
		try {
			Scanner lineScanner = new Scanner(line);
			return new TagBean(lineScanner.nextLong(), lineScanner.next());
		} catch (Exception e) {
			throw new Exception("Can not create an ArtistBean with this line: "
					+ line + ". Why? because " + e.getMessage());
		}
	}

}
