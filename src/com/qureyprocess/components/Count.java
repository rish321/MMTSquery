package com.qureyprocess.components;

import java.io.IOException;

import com.sparql.Sparql;

public class Count {

	public static String getCount(String folder, String source, String dest, String atStation,
			String srcTimeInit, String srcTimeFin, String destTimeInit,
			String destTimeFin, String set, String info, String found) throws IOException, InterruptedException {
		if(!found.equals("runs"))
		{
			if(atStation == null)
			{
				if(info == "arr")
					atStation = dest;
				else
					atStation = source;
			}
			String file = "kitne"; 
			String params = folder + file + ".sh ";
			params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " + set + " " + info + " " + found;
			params += ">" + folder + file + "temp";
			Sparql.createSparqlFile(params);
			return folder + file + "temp";
		}
		else
		{
			if(atStation == null)
			{
				if(info == "arr")
					atStation = dest;
				else
					atStation = source;
			}
			String file = "kitnedin"; 
			String params = folder + file + ".sh ";
			params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " + set + " " + info + " " + found;
			params += ">" + folder + file + "temp";
			Sparql.createSparqlFile(params);
			return folder + file + "temp";
		}
	}






}
