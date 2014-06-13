package com.qureyprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.Template.TemplatePrepare;

public class Domain {

	public static HashMap<String, String> initHm(String file)	throws FileNotFoundException, IOException {
		HashMap <String, String> hmqtype = new HashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String s;
		while((s = br.readLine()) != null)
			hmqtype.put(s.split("\t")[0], s.split("\t")[1]);
		br.close();
		return hmqtype;
	}

	public static String createDomain(String[] args)
			throws FileNotFoundException, IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0]+"prefixes")));
		
		String rdfs = br.readLine();
		String rdf = br.readLine();
		String owl = br.readLine();
		String xsd = br.readLine();
		String base = br.readLine();
		br.close();
		
		String folder = args[0]+"domainqueries/";
		
		TemplatePrepare.preProcess(args[0], rdfs, rdf, owl, xsd, base, folder, args[0]+"unprocessed/");
		return folder;
	}

}
