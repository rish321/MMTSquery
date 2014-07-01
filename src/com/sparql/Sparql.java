/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.sparql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The Class Sparql.
 */
public class Sparql {

	/**
	 * Creates the sparql file.
	 * 
	 * @param params
	 *            the params
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void createSparqlFile(String params) throws IOException, InterruptedException {
		//System.out.println(params);
		Process proc = Runtime.getRuntime().exec(new String[]
				{
				"/bin/sh", "-c", params
				});
		proc.waitFor();
		InputStream ins = proc.getErrorStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(ins));
		//String s;
		while((br.readLine())!= null);
			//System.out.println(s);
		br.close();
	}

}
