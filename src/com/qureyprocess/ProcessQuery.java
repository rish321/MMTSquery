package com.qureyprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import com.system.Setu;
import com.sparql.Sparql;

public class ProcessQuery {
	public static void main(String args[]) throws Exception
	{
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
		
		//BufferedReader br = new BufferedReader(new FileReader(new File(args[2])));
		ProcessAnswer.translate(hmtrans, "kripya sawaal poochein");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		Setu S = new Setu();
		String setu_path = S.find_setu();
		int i = 1;
		while((s = br.readLine()) != null)
		{
			if(s.startsWith("#"))
				continue;
			System.out.println("Started Answering question " + i++ + "...");
			System.out.println(s);
			s = rephraseQuery(folder, s);
			ManageArguments.preProcess(m, dm, s, directMap, hmpll, hmind, hmnum, hmtrans, folder, args[2], setu_path);
			System.out.println();
			ProcessAnswer.translate(hmtrans, "kripya sawaal poochein");
		}
		br.close();
	}
	/*public static void expandActionReason(String action, int i) throws IOException, InterruptedException
	{
		List<QuerySolution> lspcond = ProcessAnswer.getAnswer(Condition.getPrecondition(action));
		Iterator<QuerySolution> iterpcond = lspcond.iterator();
		while(iterpcond.hasNext())
		{
			String cond = iterpcond.next().get("?pcond").toString();
			printspace(i*2);
			System.out.println("Have you " + cond.substring(cond.indexOf("#")+1) + "?");
			expandCondition(cond.substring(cond.indexOf("#")+1), i+1);
		}
		List<QuerySolution> lssubact = ProcessAnswer.getAnswer(Action.getSubaction(action));
		Iterator<QuerySolution> itersubact = lssubact.iterator();
		while(itersubact.hasNext())
		{
			String acts = itersubact.next().get("?subact").toString();
			printspace(i*2);
			System.out.println("Do you " + acts.substring(acts.indexOf("#")+1) + "?");
			expandActionReason(acts.substring(acts.indexOf("#")+1), i+1);
		}
	}
	private static void expandCondition(String condition, int i) throws IOException, InterruptedException
	{
		List<QuerySolution> lsoutcome = ProcessAnswer.getAnswer(Action.getoutAction(condition));
		Iterator<QuerySolution> iterocome = lsoutcome.iterator();
		while(iterocome.hasNext())
		{
			String acts = iterocome.next().get("?action").toString();
			printspace(i*2);
			System.out.println("Did you " + acts.substring(acts.indexOf("#")+1) + "?");
			expandActionReason(acts.substring(acts.indexOf("#")+1), i+1);
		}
	}
	public static void expandAction(String action, int i) throws IOException, InterruptedException
	{
		List<QuerySolution> lssubact = ProcessAnswer.getAnswer(Action.getSubaction(action));
		Iterator<QuerySolution> itersubact = lssubact.iterator();
		while(itersubact.hasNext())
		{
			String acts = itersubact.next().get("?subact").toString();
			printspace(i*2);
			System.out.println("Do " + acts.substring(acts.indexOf("#")+1) + "...");
			expandAction(acts.substring(acts.indexOf("#")+1), i+1);
		}
	}
	private static void printspace(int i)
	{
		for(int j = 0; j < i; j++)
			System.out.print(" ");
	}*/
	public static boolean hasSubClassTransitive( OntClass parent, OntClass child ) {
		return OntTools.findShortestPath( child.getOntModel(), child, parent, new PredicatesFilter( RDFS.subClassOf ) ) != null;
	}
	static String replaceParallel(String found) {
		if(found.equals("ट्रेन") || found.equals("ट्रेने"))
			found = "ofTrain";
		if(found == "स्टेशन")
			found = "atStation";
		if(found.equals("दिन"))
			found = "runs";
		return found;
	}
	public void yes_no()
	{

	}
	public void duration(boolean pl)
	{

	}
	public void count(boolean pl)
	{

	}
	public void time(boolean pl)
	{

	}
	public void day(boolean pl)
	{

	}
	public void entity(String clss, boolean pl)
	{

	}
	public void process(boolean pl)
	{

	}
	public void description()
	{

	}
	public static String rephraseQuery(String folder, String string) throws IOException, InterruptedException {
		String file = "rephrase"; 
		String params = folder + file + ".sh ";
		String filename = "folder + tempin.txt";
		File file1 = new File(filename);
		 
		// if file doesnt exists, then create it
		if (!file1.exists()) {
			file1.createNewFile();
		}

		FileWriter fw = new FileWriter(file1.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(string);
		bw.close();
		params += filename;
		
		params += ">" + folder + file1 + "temp";
		Sparql.createSparqlFile(params);
		
		FileReader fr = new FileReader(folder + file1 + "temp"); 
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		br.close();
		return s;
	}
}