/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components;

import java.io.IOException;

/**
 * The Class Duration.
 */
public class Duration {

	/**
	 * Gets the duration list.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param source
	 *            the source
	 * @param dest
	 *            the dest
	 * @param atStation
	 *            the at station
	 * @param srcTimeInit
	 *            the src time init
	 * @param srcTimeFin
	 *            the src time fin
	 * @return the duration list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getDurationList(String folder, String foldertmp, String source, String dest,
			String atStation, String srcTimeInit, String srcTimeFin) throws IOException, InterruptedException {
		String file = "kitnikitniderstation"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " ";
		return Query.makeQuery(foldertmp, file, params);
	}

	/**
	 * Gets the duration list.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param source
	 *            the source
	 * @param dest
	 *            the dest
	 * @param srcTimeInit
	 *            the src time init
	 * @param srcTimeFin
	 *            the src time fin
	 * @param destTimeInit
	 *            the dest time init
	 * @param destTimeFin
	 *            the dest time fin
	 * @return the duration list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getDurationList(String folder, String foldertmp, String source, String dest,
			String srcTimeInit, String srcTimeFin, String destTimeInit,
			String destTimeFin) throws IOException, InterruptedException {
		String file = "kitnikitnidersimple"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + destTimeInit + " " + destTimeFin + " " + source + " " + dest;
		return Query.makeQuery(foldertmp, file, params);
	}

	/**
	 * Gets the duration.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param source
	 *            the source
	 * @param dest
	 *            the dest
	 * @param atStation
	 *            the at station
	 * @param srcTimeInit
	 *            the src time init
	 * @param srcTimeFin
	 *            the src time fin
	 * @param set
	 *            the set
	 * @return the duration
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getDuration(String folder, String foldertmp, String source, String dest,
			String atStation, String srcTimeInit, String srcTimeFin, String set) throws IOException, InterruptedException {
		String currTime = srcTimeInit;
		String file = "kitniderstation"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " +  currTime + " " + set;
		return Query.makeQuery(foldertmp, file, params);
	}

	/**
	 * Gets the duration.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param source
	 *            the source
	 * @param dest
	 *            the dest
	 * @param srcTimeInit
	 *            the src time init
	 * @param srcTimeFin
	 *            the src time fin
	 * @param destTimeInit
	 *            the dest time init
	 * @param destTimeFin
	 *            the dest time fin
	 * @param set
	 *            the set
	 * @return the duration
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getDuration(String folder, String foldertmp, String source, String dest,
			String srcTimeInit, String srcTimeFin, String destTimeInit,
			String destTimeFin, String set) throws IOException, InterruptedException {
		String file = "kitnidersimple"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + destTimeInit + " " + destTimeFin + " " + source + " " + dest + " " + set;
		return Query.makeQuery(foldertmp, file, params);
	}

}
