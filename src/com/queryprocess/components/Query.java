/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components;

import java.io.IOException;

import com.sparql.Sparql;

/**
 * The Class Query.
 */
public class Query {

	/**
	 * Make query.
	 * 
	 * @param foldertmp
	 *            the foldertmp
	 * @param file
	 *            the file
	 * @param params
	 *            the params
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String makeQuery(String foldertmp, String file, String params)
			throws IOException, InterruptedException {
		params += ">" + foldertmp + file + "temp";
		//System.out.println(params);
		Sparql.createSparqlFile(params);
		return foldertmp + file + "temp";
	}

}
