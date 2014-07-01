/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.system;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * The Class Setu.
 */
public class Setu
{
	
	/**
	 * Find_moses.
	 * 
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String find_moses() throws IOException
	{
		String user = System.getProperty("user.name");
		BufferedReader br = new BufferedReader(new FileReader(new File("/home/"+user+"/.bashrc")));
		String s;
		while((s = br.readLine()) != null)
		{
			if(s.contains("export MOSES"))
			{
				s = s.substring(s.indexOf("=")+1);
				break;
			}
		}
		br.close();
		s = s + "/";
		return s.replaceAll("\"", "").replaceAll("//", "/");
	}
	
	/**
	 * Find_setu.
	 * 
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String find_setu() throws IOException
	{
		String user = System.getProperty("user.name");
		BufferedReader br = new BufferedReader(new FileReader(new File("/home/"+user+"/.bashrc")));
		String s;
		while((s = br.readLine()) != null)
		{
			if(s.contains("export setu"))
			{
				s = s.substring(s.indexOf("=")+1);
				break;
			}
		}
		br.close();
		s = s + "/";
		return s.replaceAll("\"", "").replaceAll("//", "/");
	}
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[])
	{
		
	}
}
