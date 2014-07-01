/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components;

/**
 * The Class Set.
 */
public class Set {

	/**
	 * Gets the sets the.
	 * 
	 * @param s
	 *            the s
	 * @return the sets the
	 */
	public static String getSet(String s) {
		String set = "MIN";
		if(s.contains("पहली") || s.contains("अगली"))
			set = "MIN";
		if(s.contains("आखरी") || s.contains("आखिरी") || s.contains("पिछली"))
			set = "MAX";
		return set;
	}

}
