/**
 * 
 */
package com.md.dm.dw.lastfm;

/**
 * @author diego
 *
 */
public class ArtistCSVReader {

	private String filename;

	public ArtistCSVReader(String filename) {
		super();
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	@Override
	public String toString() {
		return "ArtistCSVReader [filename=" + filename + "]";
	}
	
}
