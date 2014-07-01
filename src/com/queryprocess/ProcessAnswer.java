/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess;

import java.util.ArrayList;
import java.util.HashMap;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 * The Class ProcessAnswer.
 */
public class ProcessAnswer {

	/**
	 * Prints the answer.
	 * 
	 * @param hmtrans
	 *            the hmtrans
	 * @param file
	 *            the file
	 * @param m
	 *            the m
	 */
	public static void printAnswer(HashMap<String, String> hmtrans, String file, OntModel m)
	{
		ArrayList<String> arr = getAnswer(file, m);
		for(int i = 0; i < arr.size(); i++)
			ProcessAnswer.translate(hmtrans, arr.get(i));
	}

	/**
	 * Gets the answer.
	 * 
	 * @param file
	 *            the file
	 * @param m
	 *            the m
	 * @return the answer
	 */
	public static ArrayList<String> getAnswer(String file, OntModel m)
	{
		ResultSet rs = getResultSet(file, m);
		ArrayList<String> arr = new ArrayList<String>();
		String str[] = ResultSetFormatter.asXMLString(rs).split(Strings.getString("newline")); //$NON-NLS-1$
		for(int i = 0; i < str.length; i++)
		{
			//System.out.println(str[i]);
			if(str[i].endsWith(Strings.getString("literal"))) //$NON-NLS-1$
			{
				String s = str[i].substring(str[i].indexOf(Strings.getString("endOfXmlPart"))+1, str[i].indexOf(Strings.getString("literal"))); //$NON-NLS-1$ //$NON-NLS-2$
				arr.add(s);
			}
			if(str[i].endsWith(Strings.getString("url"))) //$NON-NLS-1$
			{
				String s = str[i].substring(str[i].indexOf(Strings.getString("value"))+1, str[i].indexOf(Strings.getString("url"))); //$NON-NLS-1$ //$NON-NLS-2$
				arr.add(s);
			}
		}
		return arr;
	}

	/**
	 * Gets the result set.
	 * 
	 * @param file
	 *            the file
	 * @param m
	 *            the m
	 * @return the result set
	 */
	public static ResultSet getResultSet(String file, OntModel m) {
		//System.out.println(file);
		Query q = QueryFactory.read(file);
		QueryExecution qe = SparqlDLExecutionFactory.create( q, m);
		ResultSet rs = qe.execSelect();
		//ResultSetFormatter.out(rs);
		return rs;
	}

	/**
	 * Translate.
	 * 
	 * @param hmtrans
	 *            the hmtrans
	 * @param string
	 *            the string
	 */
	public static void translate(HashMap<String, String> hmtrans, String string) {
		System.out.println(string);
		if(hmtrans.get(string) != null)
			System.out.println(hmtrans.get(string));
		else
		{
			String split[] = string.replaceAll("(-)", " ").split(" "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			for(int i = 0; i < split.length; i++)
			{
				if(split[i].matches(Strings.getString("digitRegex"))) //$NON-NLS-1$
				{
					//System.out.println(split[i]);
					String splits[] = split[i].trim().split(""); //$NON-NLS-1$
					for(int j = 0; j < splits.length; j++)
						if(splits[j].length() != 0)
							System.out.print(hmtrans.get(splits[j]));//
					System.out.print(" "); //$NON-NLS-1$
				}
				else
					System.out.print(hmtrans.get(split[i]) + " "); //$NON-NLS-1$
			}
		}
		System.out.println();
	}



}
