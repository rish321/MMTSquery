/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.filldata;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The Class FillTravel.
 */
public class FillTravel {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		int init = Integer.parseInt(args[1]);
		ArrayList<String> arr = new ArrayList<String>();
		String s;
		while((s = br.readLine()) != null)
		{
			if(s.length() == 0)
				break;
			arr.add(s.trim());
		}
		s = br.readLine();
		String[] str = s.split("\t");
		for(int i = 0; i < str.length; i++)
			str[i] = str[i].trim();
		br.readLine();
		ArrayList<String[]> time = new ArrayList<String[]>();
		while((s = br.readLine()) != null)
		{
			String[] times = s.split("\t");
			for(int i = 0; i < times.length; i++)
				times[i] = times[i].trim();
			time.add(times);
		}
		br.close();
		for(int i = init; i < init+str.length; i++)
		{
			for(int j = 1; j < time.size(); j++)
			{
				System.out.print("    <Declaration>\n        <NamedIndividual IRI=\"#");
				System.out.print("travel" + i + "." + j);
				System.out.print("\"/>\n    </Declaration>\n");
			}
		}
		for(int i = init; i < init+str.length; i++)
		{
			for(int j = 1; j < time.size(); j++)
			{	
				System.out.print("    <ClassAssertion>\n        <Class IRI=\"#Travel\"/>\n        <NamedIndividual IRI=\"#");
				System.out.print("travel" + i + "." + j);
				System.out.print("\"/>\n    </ClassAssertion>\n");
			}
		}
		for(int i = init; i < init+str.length; i++)
		{
			for(int j = 1; j < time.size(); j++)
			{
			    System.out.print("    <ObjectPropertyAssertion>\n        <ObjectProperty IRI=\"#precondition\"/>\n        <NamedIndividual IRI=\"#");
			    System.out.print("travel" + i + "." + j);
			    System.out.print("\"/>\n        <NamedIndividual IRI=\"#");
			    System.out.print("stop" + i + "." + j);
			    System.out.print("\"/>\n    </ObjectPropertyAssertion>\n");
			    
			    System.out.print("    <ObjectPropertyAssertion>\n        <ObjectProperty IRI=\"#hasOutcome\"/>\n        <NamedIndividual IRI=\"#");
			    System.out.print("travel" + i + "." + j);
			    System.out.print("\"/>\n        <NamedIndividual IRI=\"#");
			    System.out.print("stop" + i + "." + (j+1));
			    System.out.print("\"/>\n    </ObjectPropertyAssertion>\n");
			    
			}
		}
	}
}
