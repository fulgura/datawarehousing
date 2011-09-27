/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.io.File;
import java.util.Scanner;

import com.md.dm.dw.lastfm.model.Artist;

/**
 * @author diego
 * 
 */
public class ArtistCSVReader {

	private String filename;
	private Scanner scanner;

	public ArtistCSVReader(String filename) throws Exception {
		super();
		this.filename = filename;
		scanner = new Scanner(new File(filename), "UTF-8");
	}

	public String getFilename() {
		return filename;
	}

	@Override
	public String toString() {
		return "ArtistCSVReader [filename=" + filename + "]";
	}

	/**
	 * Returns the next Artist in filename. It uses an instance of Scanner to do
	 * this.
	 * 
	 * @return a new instance of {@link Artist} or null if doesn't exist more
	 */
	public Artist nextArtist() {
		Artist artist = null;
		if (scanner.hasNextLine()) {
			String aLine = scanner.nextLine();
			Scanner lineScanner = new Scanner(aLine);
			artist = new Artist(lineScanner.nextLong(), lineScanner.next(),
					lineScanner.next(), lineScanner.next());
		}

		return artist;
	}
	/**
	 * Returns true if exists another line with an artist information in the file.
	 * 
	 * @return a boolean valu which will determine if exists or not more artis to process
	 */
	public boolean hasMoreArtist() {
		return scanner.hasNextLine();
	}

}
