/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components;

import java.io.IOException;

/**
 * The Class Count.
 */
public class Count {

	/**
	 * Gets the count.
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
	 * @param destTimeInit
	 *            the dest time init
	 * @param destTimeFin
	 *            the dest time fin
	 * @param set
	 *            the set
	 * @param info
	 *            the info
	 * @param found
	 *            the found
	 * @return the count
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getCount(String folder, String foldertmp, String source, String dest, String atStation,
			String srcTimeInit, String srcTimeFin, String destTimeInit,
			String destTimeFin, String set, String info, String found) throws IOException, InterruptedException {
		if(!found.equals("runs"))
		{
			if(atStation == null)
			{
				if(info == "arr")
					atStation = dest;
				else
					atStation = source;
			}
			String file = "kitne"; 
			String params = folder + file + ".sh ";
			params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " + set + " " + info + " " + found;
			return Query.makeQuery(foldertmp, file, params);
		}
		else
		{
			if(atStation == null)
			{
				if(info == "arr")
					atStation = dest;
				else
					atStation = source;
			}
			String file = "kitnedin"; 
			String params = folder + file + ".sh ";
			params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " + set + " " + info + " " + found;
			return Query.makeQuery(foldertmp, file, params);
		}
	}
}
