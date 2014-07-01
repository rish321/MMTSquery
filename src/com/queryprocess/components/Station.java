/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The Class Station.
 */
public class Station {

	/**
	 * Gets the destination.
	 * 
	 * @param s
	 *            the s
	 * @param hmind
	 *            the hmind
	 * @param source
	 *            the source
	 * @return the destination
	 */
	@SuppressWarnings("rawtypes")
	public static String getDestination(String s, HashMap<String, String> hmind, String source) {
		Iterator it = hmind.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			if(s.contains(pairs.getKey() + " जाने वाली") || s.contains(pairs.getKey() + " के_लिए")
					|| s.contains(pairs.getKey() + " की") || s.contains(pairs.getKey() + " को")
					|| s.contains(pairs.getKey() + " तक" )){
				return (String) pairs.getValue();

			}
			//it.remove(); // avoids a ConcurrentModificationException
		}	
		Iterator it1 = hmind.entrySet().iterator();
		while (it1.hasNext()) {
			Map.Entry pairs1 = (Map.Entry)it1.next();
			if(s.contains(pairs1.getKey() + " ")){

				String dest=(String) pairs1.getValue();
				System.out.println(dest);

				if(!dest.equals(source)){
					return dest;
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets the at station.
	 * 
	 * @param s
	 *            the s
	 * @param hmind
	 *            the hmind
	 * @param source
	 *            the source
	 * @param dest
	 *            the dest
	 * @return the at station
	 */
	@SuppressWarnings("rawtypes")
	public static String getAtStation(String s, HashMap<String, String> hmind, String source, String dest) {
		Iterator it = hmind.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			if(s.contains(pairs.getKey() + " पर") || s.contains(pairs.getKey() + " में"))
				return (String) pairs.getValue();
		}
		//it.remove(); // avoids a ConcurrentModificationException
		Iterator it1 = hmind.entrySet().iterator();
		while (it1.hasNext()) {
			Map.Entry pairs1 = (Map.Entry)it1.next();
			if(s.contains(pairs1.getKey() + " ")){
				String atstat=(String) pairs1.getValue();
				if(!atstat.equals(source) && !atstat.equals(dest))
					return atstat;
			}
		}
		return null;
	}
	
	/**
	 * Gets the source.
	 * 
	 * @param s
	 *            the s
	 * @param hmind
	 *            the hmind
	 * @return the source
	 */
	@SuppressWarnings("rawtypes")
	public static String getSource(String s, HashMap<String, String> hmind) {
		Iterator it = hmind.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			if(s.contains(pairs.getKey() + " से"))
				return (String) pairs.getValue();
			//it.remove(); // avoids a ConcurrentModificationException
		}
		return null;
	}
	
	/**
	 * Gets the station.
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
	 * @param info
	 *            the info
	 * @return the station
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getStation(String folder, String foldertmp, String source, String dest,
			String srcTimeInit, String srcTimeFin, String destTimeInit, String destTimeFin,
			String set, String info) throws IOException, InterruptedException {
		String file = "kahan"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + set + " " + info;
		return Query.makeQuery(foldertmp, file, params);
	}
	
	/**
	 * Gets the intermediate station.
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
	 * @param info
	 *            the info
	 * @return the intermediate station
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String getIntermediateStation(String folder, String foldertmp, String source, String dest,
			String srcTimeInit, String srcTimeFin, String destTimeInit, String destTimeFin,
			String set, String info) throws IOException, InterruptedException {
		String file = "kahankahan"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + set + " " + info;
		return Query.makeQuery(foldertmp, file, params);
	}
}
