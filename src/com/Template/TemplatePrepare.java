/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.Template;

import java.io.IOException;

import com.sparql.Sparql;

/**
 * The Class TemplatePrepare.
 */
public class TemplatePrepare {

	/**
	 * Pre process.
	 * 
	 * @param init_folder
	 *            the init_folder
	 * @param rdfs
	 *            the rdfs
	 * @param rdf
	 *            the rdf
	 * @param owl
	 *            the owl
	 * @param xsd
	 *            the xsd
	 * @param base
	 *            the base
	 * @param folder
	 *            the folder
	 * @param curr
	 *            the curr
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void preProcess(String init_folder, String rdfs, String rdf, String owl, String xsd, String base, String folder, String curr) throws IOException, InterruptedException
	{
		Sparql.createSparqlFile(init_folder+Strings.getString("initializeall") +  rdfs + " " + rdf + " " + owl + " " + xsd + " " + base + " " + folder + " " + curr); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
	}
}
