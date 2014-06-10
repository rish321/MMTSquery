package com.qureyprocess.components.action.semanticroles;

import java.io.IOException;

import com.sparql.Sparql;

public class Theme {
	public static String getTheme(String folder, String string) throws IOException, InterruptedException {
		String file = "findTheme"; 
		String params = folder + file + ".sh ";
		params += string;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}
}
