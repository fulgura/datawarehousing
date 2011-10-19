/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.util.Scanner;

import com.md.dm.dw.lastfm.entity.TagBean;
import com.md.dm.dw.lastfm.entity.UserBean;

/**
 * Crates an instance of {@link UserBean} implementing a Strategy pattern. For more
 * details see {@link LineParseStrategy}
 * 
 * @author diego
 * 
 */
public class UserLineParseStrategy implements LineParseStrategy<UserBean> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.md.dm.dw.lastfm.IntanceCreatorStrategy#create(java.lang.String)
	 */
	@Override
	public UserBean create(String line) throws Exception {
		try {
			Scanner lineScanner = new Scanner(line);
			return new UserBean(lineScanner.nextLong(), "N/S");
		} catch (Exception e) {
			throw new Exception("Can not create an UserBean with this line: "
					+ line + ". Why? because " + e.getMessage());
		}
	}

}
