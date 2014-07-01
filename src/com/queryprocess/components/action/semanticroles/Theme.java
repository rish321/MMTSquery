/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components.action.semanticroles;

import java.io.IOException;

import com.queryprocess.components.Query;

/**
 * The Class Theme.
 */
public class Theme {
	
	/**
	 * Gets the theme.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param string
	 *            the string
	 * @return the theme
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getTheme(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findTheme"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}
}
