package com.qureyprocess;

import java.util.ArrayList;
import java.util.HashMap;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class ProcessAnswer {

	public static void printAnswer(HashMap<String, String> hmtrans, String file, OntModel m)
	{
		ArrayList<String> arr = getAnswer(file, m);
		for(int i = 0; i < arr.size(); i++)
			ProcessAnswer.translate(hmtrans, arr.get(i));
	}

	public static ArrayList<String> getAnswer(String file, OntModel m)
	{
		ResultSet rs = getResultSet(file, m);
		ArrayList<String> arr = new ArrayList<String>();
		String str[] = ResultSetFormatter.asXMLString(rs).split("\n");
		for(int i = 0; i < str.length; i++)
		{
			//System.out.println(str[i]);
			if(str[i].endsWith("</literal>"))
			{
				String s = str[i].substring(str[i].indexOf(">")+1, str[i].indexOf("</literal>"));
				arr.add(s);
			}
			if(str[i].endsWith("</uri>"))
			{
				String s = str[i].substring(str[i].indexOf("#")+1, str[i].indexOf("</uri>"));
				arr.add(s);
			}
		}
		return arr;
	}

	public static ResultSet getResultSet(String file, OntModel m) {
		//System.out.println(file);
		Query q = QueryFactory.read(file);
		QueryExecution qe = SparqlDLExecutionFactory.create( q, m);
		ResultSet rs = qe.execSelect();
		//ResultSetFormatter.out(rs);
		return rs;
	}

	public static void translate(HashMap<String, String> hmtrans, String string) {
		System.out.println(string);
		if(hmtrans.get(string) != null)
			System.out.println(hmtrans.get(string));
		else
		{
			String split[] = string.replaceAll("(-)", " ").split(" ");
			for(int i = 0; i < split.length; i++)
			{
				if(split[i].matches(".*\\d+.*"))
				{
					//System.out.println(split[i]);
					String splits[] = split[i].trim().split("");
					for(int j = 0; j < splits.length; j++)
						if(splits[j].length() != 0)
							System.out.print(hmtrans.get(splits[j]));//
					System.out.print(" ");
				}
				else
					System.out.print(hmtrans.get(split[i]) + " ");
			}
		}
		System.out.println();
	}



}
