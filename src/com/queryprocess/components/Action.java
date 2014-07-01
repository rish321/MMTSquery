/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components;

import java.io.IOException;

/**
 * The Class Action.
 */
public class Action {

	/**
	 * Gets the action.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param action
	 *            the action
	 * @param theme
	 *            the theme
	 * @return the action
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getAction(String folder, String foldertmp, String action, String theme) throws IOException, InterruptedException {
		String file = "findAction"; 
		String params = folder + file + ".sh ";
		params += action + " " + theme;
		return Query.makeQuery(foldertmp, file, params);
	}

	/**
	 * Gets the subaction.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param string
	 *            the string
	 * @return the subaction
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getSubaction(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findSubaction"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}

	/**
	 * Gets the out action.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param string
	 *            the string
	 * @return the out action
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getoutAction(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findOutAction"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}

	/**
	 * Gets the pre action.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param string
	 *            the string
	 * @return the pre action
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getpreAction(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findPreAction"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}
	
}