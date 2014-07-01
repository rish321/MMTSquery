/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.ontologicalMapping;

/**
 * The Class ReplaceParallel.
 */
public class ReplaceParallel {

	/**
	 * Replace parallel.
	 * 
	 * @param found
	 *            the found
	 * @return the string
	 */
	public static String replaceParallel(String found) {
		if(found.equals("ट्रेन") || found.equals("ट्रेने"))
			found = "ofTrain";
		if(found == "स्टेशन")
			found = "atStation";
		if(found.equals("दिन"))
			found = "runs";
		return found;
	}

}
