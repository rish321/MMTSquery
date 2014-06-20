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

public class ProcessQuery {
	public static void main(String args[]) throws Exception
	{
		String foldertmp = ".tmp/";
		new File(foldertmp).mkdir();
		OntModel m = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
		String	ontology = args[3];
		m.read( ontology );
		String folder = Domain.createDomain(args);
		Dialog dm = new Dialog();
		HashMap <String, String> directMap = Domain.initHm(args[0] + "directmap");
		HashMap <String, String> hmpll = Domain.initHm(args[0]+"parallel");
		HashMap <String, String> hmind = Domain.initHm(args[0]+"individuals_parallel");
		HashMap <String, String> hmnum = Domain.initHm(args[0]+"number");
		HashMap <String, String> hmtrans = Domain.initHm(args[0]+"enghin");
		ProcessAnswer.translate(hmtrans, "kripya sawaal poochein");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader(new File("/home/pramesh/Desktop/IIIT-H/query.out")));
		String s;
		Setu S = new Setu();
		String setu_path = S.find_setu();
		int i = 1;
		while((s = br.readLine()) != null)
		{
			if(s.startsWith("#"))
				continue;
			System.out.println("Started Answering question " + i++ + "...");
			s = rephraseQuery(folder, s, args[2], setu_path);
			ManageArguments.preProcess(m, dm, s, directMap, hmpll, hmind, hmnum, hmtrans, folder, foldertmp, args[2], setu_path);
			System.out.println();
			ProcessAnswer.translate(hmtrans, "kripya sawaal poochein");
		}
		br.close();
	}
	public static boolean hasSubClassTransitive( OntClass parent, OntClass child ) {
		return OntTools.findShortestPath( child.getOntModel(), child, parent, new PredicatesFilter( RDFS.subClassOf ) ) != null;
	}
	public static String rephraseQuery(String folder, String string, String NLPfolder, String setu_path) throws IOException, InterruptedException { 
		String params = folder + "rephrase.sh ";
		String filename = folder + "tempin.txt";
		String dir = System.getProperty("user.dir");
		File file1 = new File(filename);
		FileWriter fw = new FileWriter(file1);
		fw.write(string);
		fw.close();
		params += (" " + filename + " " + NLPfolder + " " + dir + " " + setu_path);
		params += ">" + filename + "temp";
		Sparql.createSparqlFile(params);
		BufferedReader br = new BufferedReader(new FileReader(new File(filename + "temp")));
		String s = br.readLine();
		br.close();
		return s;
	}
}