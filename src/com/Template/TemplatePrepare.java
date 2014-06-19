package com.Template;

import java.io.IOException;

import com.sparql.Sparql;

public class TemplatePrepare {

	public static void preProcess(String init_folder, String rdfs, String rdf, String owl, String xsd, String base, String folder, String curr) throws IOException, InterruptedException
	{
		Sparql.createSparqlFile(init_folder+"initializeall.sh " +  rdfs + " " + rdf + " " + owl + " " + xsd + " " + base + " " + folder + " " + curr);
	}
}
