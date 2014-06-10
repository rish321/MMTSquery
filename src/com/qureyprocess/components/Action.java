package com.qureyprocess.components;

import java.io.IOException;

import com.sparql.Sparql;

public class Action {

	public static String getSubaction(String folder, String string) throws IOException, InterruptedException {
		String file = "findSubaction"; 
		String params = folder + file + ".sh ";
		params += string;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}

	public static String getoutAction(String folder, String string) throws IOException, InterruptedException {
		String file = "findOutAction"; 
		String params = folder + file + ".sh ";
		params += string;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}

	public static String getpreAction(String folder, String string) throws IOException, InterruptedException {
		String file = "findPreAction"; 
		String params = folder + file + ".sh ";
		params += string;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}
	
}