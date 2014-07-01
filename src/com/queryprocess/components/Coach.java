/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components;

import java.io.IOException;
import java.util.HashMap;

/**
 * The Class Coach.
 */
public class Coach {

	/**
	 * Gets the coach.
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
	 * @return the coach
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getCoach(String folder, String foldertmp, String source, String dest, String atStation,
			String srcTimeInit, String srcTimeFin, String destTimeInit,
			String destTimeFin, String set, String info) throws IOException, InterruptedException {
			if(atStation == null)
			{
				if(info == "arr")
					atStation = dest;
				else
					atStation = source;
			}
			String file = "kya_coach"; 
			String params = folder + file + ".sh ";
			params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " + set + " " + info;
			return Query.makeQuery(foldertmp, file, params);

	}

	/**
	 * Extract coach type.
	 * 
	 * @param s
	 *            the s
	 * @param directMap
	 *            the direct map
	 * @return the string
	 */
	public static String extractCoachType(String s,
			HashMap<String, String> directMap) {
		String[] words = s.split(" ");
		String coachType="";
		for(int i = 0; i < words.length; i++)
			if(words[i].equals("डब्बा ") || words[i].equals("बोगी ") || words[i].equals("कोच"))
				coachType=directMap.get(words[i-1]+ " कोच");
		return coachType;
	}
}