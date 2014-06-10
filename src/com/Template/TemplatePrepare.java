package com.Template;

import java.io.IOException;

import com.sparql.Sparql;

public class TemplatePrepare {

	public static void preProcess(String rdfs, String rdf, String owl, String xsd, String base, String folder) throws IOException, InterruptedException
	{
		Sparql.createSparqlFile("/home/rishabh/tools/pellet-2.3.1/initializeall.sh " +  rdfs + " " + rdf + " " + owl + " " + xsd + " " + base + " " + folder);
	}
}
