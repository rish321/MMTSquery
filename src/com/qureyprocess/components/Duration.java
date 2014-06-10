package com.qureyprocess.components;

import java.io.IOException;

import com.sparql.Sparql;

public class Duration {

	public static String getDurationList(String folder, String source, String dest,
			String atStation, String srcTimeInit, String srcTimeFin) throws IOException, InterruptedException {
		String file = "kitnikitniderstation"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " ";
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}

	public static String getDurationList(String folder, String source, String dest,
			String srcTimeInit, String srcTimeFin, String destTimeInit,
			String destTimeFin) throws IOException, InterruptedException {
		String file = "kitnikitnidersimple"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + destTimeInit + " " + destTimeFin + " " + source + " " + dest;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}

	public static String getDuration(String folder, String source, String dest,
			String atStation, String srcTimeInit, String srcTimeFin, String set) throws IOException, InterruptedException {
		String currTime = srcTimeInit;
		String file = "kitniderstation"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " +  currTime + " " + set;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}

	public static String getDuration(String folder, String source, String dest,
			String srcTimeInit, String srcTimeFin, String destTimeInit,
			String destTimeFin, String set) throws IOException, InterruptedException {
		String file = "kitnidersimple"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + destTimeInit + " " + destTimeFin + " " + source + " " + dest + " " + set;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}

}
