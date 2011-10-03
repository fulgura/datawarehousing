/**
 * 
 */
package com.md.dm.dw.lastfm;

import java.io.File;
import java.util.Scanner;

/**
 * @author diego
 * 
 */
public class InstanceCreator<E> {

	private String filename;
	private Scanner scanner;
	private LineParseStrategy<E> lineParseStrategy;

	public InstanceCreator(String filename,
			LineParseStrategy<E> lineParseStrategy) throws Exception {
		this.filename = filename;
		this.lineParseStrategy = lineParseStrategy;
		scanner = new Scanner(new File(filename), "UTF-8");
		scanner.useDelimiter("\t");
	}

	public String getFilename() {
		return filename;
	}

	public LineParseStrategy<?> getLineParseStrategy() {
		return lineParseStrategy;
	}

	@Override
	public String toString() {
		return "InstanceCreator [filename=" + filename + ", scanner=" + scanner
				+ ", lineParseStrategy=" + lineParseStrategy + "]";
	}

	/**
	 * Returns the next instance in file. It uses an instance of Scanner to do
	 * this.
	 * 
	 * @return a new instance or null if doesn't exist more
	 */
	public E nextInstance() throws Exception {
		if (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			return lineParseStrategy.create(nextLine);
		}

		return null;
	}

	/**
	 * Returns true if exists another line with an artist information in the
	 * file.
	 * 
	 * @return a boolean valu which will determine if exists or not more artis
	 *         to process
	 */
	public boolean hasMoreInstances() {
		return scanner.hasNextLine();
	}

}
