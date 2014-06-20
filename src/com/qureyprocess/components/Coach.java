package com.qureyprocess.components;

import java.io.IOException;
import java.util.HashMap;

public class Coach {

	public static String getCoach(String folder, String foldertmp, String source, String dest, String atStation,
			String srcTimeInit, String srcTimeFin, String destTimeInit,
			String destTimeFin, String set, String info) throws IOException, InterruptedException {
			if(atStation == null)
			{
				if(info == "arr")
					atStation = dest;
				else
					atStation = source;
			}
			String file = "kya_coach"; 
			String params = folder + file + ".sh ";
			params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " + set + " " + info;
			return Query.makeQuery(foldertmp, file, params);

	}

	public static String extractCoachType(String s,
			HashMap<String, String> directMap) {
		String[] words = s.split(" ");
		String coachType="";
		for(int i = 0; i < words.length; i++)
			if(words[i].equals("डब्बा ") || words[i].equals("बोगी ") || words[i].equals("कोच"))
				coachType=directMap.get(words[i-1]+ " कोच");
		return coachType;
	}
}