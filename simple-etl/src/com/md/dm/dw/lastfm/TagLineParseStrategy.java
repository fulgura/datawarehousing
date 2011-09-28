/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.util.Scanner;

import com.md.dm.dw.lastfm.model.Tag;

/**
 * Crates an instance of {@link Tag} implementing a Strategy pattern. For more
 * details see {@link LineParseStrategy}
 * 
 * @author diego
 * 
 */
public class TagLineParseStrategy implements LineParseStrategy<Tag> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.IntanceCreatorStrategy#create(java.lang.String)
	 */
	@Override
	public Tag create(String line) throws Exception {
		try {
			Scanner lineScanner = new Scanner(line);
			return new Tag(lineScanner.nextLong(), lineScanner.next());
		} catch (Exception e) {
			throw new Exception("Can not create an Artist with this line: "
					+ line + ". Why? because " + e.getMessage());
		}
	}

}
