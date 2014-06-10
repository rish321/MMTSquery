package com.qureyprocess.components;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sparql.Sparql;

public class Station {

	@SuppressWarnings("rawtypes")
	public static String getDestination(String s, HashMap<String, String> hmind) {
		Iterator it = hmind.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			if(s.contains(pairs.getKey() + " जाने वाली") || s.contains(pairs.getKey() + " के लिए")
					|| s.contains(pairs.getKey() + " की") || s.contains(pairs.getKey() + " को")
					|| s.contains(pairs.getKey() + " तक"))
				return (String) pairs.getValue();
			//it.remove(); // avoids a ConcurrentModificationException
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static String getAtStation(String s, HashMap<String, String> hmind) {
		Iterator it = hmind.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			if(s.contains(pairs.getKey() + " पर") || s.contains(pairs.getKey() + " में"))
				return (String) pairs.getValue();
			//it.remove(); // avoids a ConcurrentModificationException
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static String getSource(String s, HashMap<String, String> hmind) {
		Iterator it = hmind.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			if(s.contains(pairs.getKey() + " से"))
				return (String) pairs.getValue();
			//it.remove(); // avoids a ConcurrentModificationException
		}
		return null;
	}
	
	public static String getStation(String folder, String source, String dest,
			String srcTimeInit, String srcTimeFin, String destTimeInit, String destTimeFin,
			String set, String info) throws IOException, InterruptedException {
		String file = "kahan"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + set + " " + info;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}

	public static String getIntermediateStation(String folder, String source, String dest,
			String srcTimeInit, String srcTimeFin, String destTimeInit, String destTimeFin,
			String set, String info) throws IOException, InterruptedException {
		String file = "kahankahan"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + set + " " + info;
		params += ">" + folder + file + "temp";
		Sparql.createSparqlFile(params);
		return folder + file + "temp";
	}
}
