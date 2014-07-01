/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.Template.TemplatePrepare;

/**
 * The Class Domain.
 */
public class Domain {

	/**
	 * Inits the hm.
	 * 
	 * @param file
	 *            the file
	 * @return the hash map
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static HashMap<String, String> initHm(String file)	throws FileNotFoundException, IOException {
		HashMap <String, String> hmqtype = new HashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String s;
		while((s = br.readLine()) != null)
			hmqtype.put(s.split(Strings.getString("tab"))[0], s.split(Strings.getString("tab"))[1]); //$NON-NLS-1$ //$NON-NLS-2$
		br.close();
		return hmqtype;
	}

	/**
	 * Creates the domain.
	 * 
	 * @param args
	 *            the args
	 * @return the string
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String createDomain(String[] args)
			throws FileNotFoundException, IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0]+Strings.getString("prefixesFile")))); //$NON-NLS-1$
		
		String rdfs = br.readLine();
		String rdf = br.readLine();
		String owl = br.readLine();
		String xsd = br.readLine();
		String base = br.readLine();
		br.close();
		
		String folder = args[0]+Strings.getString("domainQueriesFolder"); //$NON-NLS-1$
		
		TemplatePrepare.preProcess(args[0], rdfs, rdf, owl, xsd, base, folder, args[0]+Strings.getString("unprocessedFolder")); //$NON-NLS-1$
		return folder;
	}

}
