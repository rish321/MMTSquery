/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components;

import java.io.IOException;

/**
 * The Class Condition.
 */
public class Condition {

	/**
	 * Gets the precondition.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param string
	 *            the string
	 * @return the precondition
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getPrecondition(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findPrecondition"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}

	/**
	 * Gets the outcome.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param string
	 *            the string
	 * @return the outcome
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getOutcome(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findOutcome"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}

}
