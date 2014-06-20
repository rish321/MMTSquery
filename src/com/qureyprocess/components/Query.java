package com.qureyprocess.components;

import java.io.IOException;

import com.sparql.Sparql;

public class Query {

	public static String makeQuery(String foldertmp, String file, String params)
			throws IOException, InterruptedException {
		params += ">" + foldertmp + file + "temp";
		//System.out.println(params);
		Sparql.createSparqlFile(params);
		return foldertmp + file + "temp";
	}

}
