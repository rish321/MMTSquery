package com.queryprocess.components;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {

	public static String getDestTimeFin(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getDestTimeInit(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static String getSrcTimeFin(String s, HashMap<String, String> hmnum) {
		Iterator it = hmnum.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			s = s.replaceAll((String)pairs.getKey(), (String)pairs.getValue());
			it.remove();
		}
		Pattern pattern = Pattern.compile("\\d(\\d*)(|:(\\d*))(| बज) (तक|के बीच)");
		Matcher matcher = pattern.matcher(s);
		if(matcher.find())
		{
			String found = matcher.group();
			if(found.contains("बज"))
			{
				if(found.contains("तक"))
					found = found.substring(0, found.indexOf(" बज तक"));
				else
					found = found.substring(0, found.indexOf(" बज के बीच"));
			}
			else
			{
				if(found.contains("तक"))
					found = found.substring(0, found.indexOf(" तक"));
				else
					found = found.substring(0, found.indexOf(" के बीच"));
			}
			if(!found.contains(":"))
				found += ":00";
			if(found.length() == 4)
				found = "0" + found;
			return found;
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static String getSrcTimeInit(String s, HashMap<String, String> hmnum) {
		Iterator it = hmnum.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			s = s.replaceAll((String)pairs.getKey(), (String)pairs.getValue());
			it.remove();
		}
		Pattern pattern = Pattern.compile("\\d(\\d*)(|:(\\d*))(| बज) (से|के बाद)");
		Matcher matcher = pattern.matcher(s);
		if(matcher.find())
		{
			String found = matcher.group();
			if(found.contains("बज"))
			{
				if(found.contains("से"))
					found = found.substring(0, found.indexOf(" बज से"));
				else
					found = found.substring(0, found.indexOf(" बज के बाद"));
			}
			else
			{
				if(found.contains("से"))
					found = found.substring(0, found.indexOf(" से"));
				else
					found = found.substring(0, found.indexOf(" के बाद"));
			}
			if(!found.contains(":"))
				found += ":00";
			if(found.length() == 4)
				found = "0" + found;
			return found;
		}
		if(s.contains("अगली") || s.contains("पिछली")){
			

			   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			   Date date = new Date();
			   Calendar cal = Calendar.getInstance();
			   String st=dateFormat.format(cal.getTime()).toString();
			   String st1= st.substring(11,16);
			  // System.out.println(st1);
			return st1;
		}
		
		return null;
	}

	public static String getTime(String folder, String foldertmp, String source, String dest, String atStation,
			String srcTimeInit, String srcTimeFin, String destTimeInit,
			String destTimeFin, String set, String info) throws IOException, InterruptedException {
		if(atStation == null)
		{
			if(info == "arr")
				atStation = dest;
			else
				atStation = source;
		}
		String file = "kab"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " + set + " " + info;
		return Query.makeQuery(foldertmp, file, params);
	}

	public static String getTimeList(String folder, String foldertmp, String source, String dest, 
			String atStation, String srcTimeInit, String srcTimeFin,
			String destTimeInit, String destTimeFin, String set, String info) throws InterruptedException, IOException {
		if(atStation == null)
		{
			if(info == "arr")
				atStation = dest;
			else
				atStation = source;
		}
		String file = "kabkab"; 
		String params = folder + file + ".sh ";
		params += srcTimeInit + " " + srcTimeFin + " " + source + " " + dest + " " + atStation + " " + set + " " + info;
		return Query.makeQuery(foldertmp, file, params);
	}
}