package com.qureyprocess.components;

import java.io.IOException;

import com.sparql.Sparql;

public class Condition {

	public static String getPrecondition(String folder, String string) throws IOException, InterruptedException {
		String file = "findPrecondition"; 
		String params = folder + file + ".sh ";
		params += string;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}

	public static String getOutcome(String folder, String string) throws IOException, InterruptedException {
		String file = "findOutcome"; 
		String params = folder + file + ".sh ";
		params += string;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}

}
