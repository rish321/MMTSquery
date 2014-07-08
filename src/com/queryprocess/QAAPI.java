/**
 * 
 */
package com.queryprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.dialogmanager.Dialog;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.system.Setu;

/**
 * @author Rishabh Srivastava
 * @organization IIIT Hyderabad
 * @center LTRC
 */
public class QAAPI {

	private static String[] args = {"/home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/", "/home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/", "/home/rishabh/workspace/MmtsProcessing/NLP/", "/home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/MMTS_test.rdf"};
	private static String foldertmp = null;
	private static OntModel m = null;
	private static String folder = null;
	private static Dialog dm = null;
	private static HashMap <String, String> directMap = null;
	private static HashMap <String, String> hmpll = null;
	private static HashMap <String, String> hmind = null;
	private static HashMap <String, String> hmnum = null;
	private static HashMap <String, String> hmtrans = null;
	private static String setu_path = null;

	public QAAPI() throws FileNotFoundException, IOException, InterruptedException
	{
		foldertmp = Strings.getString("tmpDirectory"); //$NON-NLS-1$
		new File(foldertmp).mkdir();
		m = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
		String ontology = args[3];
		m.read( ontology );
		folder = Domain.createDomain(args);
		dm = new Dialog();
		directMap = Domain.initHm(args[0] + Strings.getString("directMapFile")); //$NON-NLS-1$
		hmpll = Domain.initHm(args[0]+Strings.getString("parallelFile")); //$NON-NLS-1$
		hmind = Domain.initHm(args[0]+Strings.getString("parallelIndividualsFile")); //$NON-NLS-1$
		hmnum = Domain.initHm(args[0]+Strings.getString("parallelNumberFile")); //$NON-NLS-1$
		hmtrans = Domain.initHm(args[0]+Strings.getString("englishHindiMappingFile")); //$NON-NLS-1$
		Setu S = new Setu();
		setu_path = S.find_setu();
	}	
	public String MMTSQAAPI(String s) throws IOException, InterruptedException, Exception
	{
		return ProcessQuery.findAnswer(s, args, foldertmp, m, folder, dm, directMap, hmpll, hmind, hmnum, hmtrans, setu_path);
	}
	public static void main(String args[])
	{
		
	}
}
