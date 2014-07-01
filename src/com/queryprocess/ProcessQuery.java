/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.dialogmanager.Dialog;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntTools;
import com.hp.hpl.jena.ontology.OntTools.PredicatesFilter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.sparql.Sparql;
import com.system.Setu;

/**
 * The Class ProcessQuery.
 */
public class ProcessQuery {
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String args[]) throws Exception
	{
		String foldertmp = Strings.getString("tmpDirectory"); //$NON-NLS-1$
		new File(foldertmp).mkdir();
		OntModel m = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
		String	ontology = args[3];
		m.read( ontology );
		String folder = Domain.createDomain(args);
		Dialog dm = new Dialog();
		HashMap <String, String> directMap = Domain.initHm(args[0] + Strings.getString("directMapFile")); //$NON-NLS-1$
		HashMap <String, String> hmpll = Domain.initHm(args[0]+Strings.getString("parallelFile")); //$NON-NLS-1$
		HashMap <String, String> hmind = Domain.initHm(args[0]+Strings.getString("parallelIndividualsFile")); //$NON-NLS-1$
		HashMap <String, String> hmnum = Domain.initHm(args[0]+Strings.getString("parallelNumberFile")); //$NON-NLS-1$
		HashMap <String, String> hmtrans = Domain.initHm(args[0]+Strings.getString("englishHindiMappingFile")); //$NON-NLS-1$
		ProcessAnswer.translate(hmtrans, Strings.getString("pleaseAskQuestion")); //$NON-NLS-1$
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader(new File("/home/pramesh/Desktop/IIIT-H/query.out")));
		String s;
		Setu S = new Setu();
		String setu_path = S.find_setu();
		int i = 1;
		while((s = br.readLine()) != null)
		{
			if(s.startsWith(Strings.getString("queryComment"))) //$NON-NLS-1$
				continue;
			System.out.println(Strings.getString("StartedAnswering") + i++ + Strings.getString("andSoOn")); //$NON-NLS-1$ //$NON-NLS-2$
			s = rephraseQuery(folder, s, args[2], setu_path);
			ManageArguments.preProcess(m, dm, s, directMap, hmpll, hmind, hmnum, hmtrans, folder, foldertmp, args[2], setu_path);
			System.out.println();
			ProcessAnswer.translate(hmtrans, Strings.getString("pleaseaAskQuestion")); //$NON-NLS-1$
		}
		br.close();
	}
	
	/**
	 * Checks for sub class transitive.
	 * 
	 * @param parent
	 *            the parent
	 * @param child
	 *            the child
	 * @return true, if successful
	 */
	public static boolean hasSubClassTransitive( OntClass parent, OntClass child ) {
		return OntTools.findShortestPath( child.getOntModel(), child, parent, new PredicatesFilter( RDFS.subClassOf ) ) != null;
	}
	
	/**
	 * Rephrase query.
	 * 
	 * @param folder
	 *            the folder
	 * @param string
	 *            the string
	 * @param NLPfolder
	 *            the NL pfolder
	 * @param setu_path
	 *            the setu_path
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String rephraseQuery(String folder, String string, String NLPfolder, String setu_path) throws IOException, InterruptedException { 
		String params = folder + Strings.getString("rephrase"); //$NON-NLS-1$
		String filename = folder + Strings.getString("tempin.txt"); //$NON-NLS-1$
		String dir = System.getProperty(Strings.getString("userDir")); //$NON-NLS-1$
		File file1 = new File(filename);
		FileWriter fw = new FileWriter(file1);
		fw.write(string);
		fw.close();
		params += (" " + filename + " " + NLPfolder + " " + dir + " " + setu_path); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		params += Strings.getString("redirection") + filename + Strings.getString("temp"); //$NON-NLS-1$ //$NON-NLS-2$
		Sparql.createSparqlFile(params);
		BufferedReader br = new BufferedReader(new FileReader(new File(filename + Strings.getString("temp")))); //$NON-NLS-1$
		String s = br.readLine();
		br.close();
		return s;
	}
}