package com.qureyprocess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dialogmanager.Dialog;
import com.hp.hpl.jena.ontology.OntModel;
import com.qureyprocess.components.Set;
import com.qureyprocess.components.Station;
import com.qureyprocess.components.Time;

public class ManageArguments {

	public static void preProcess(OntModel m, Dialog dm, String s, HashMap<String, String> directMap, HashMap<String, String> hmpll, HashMap<String, String> hmind, HashMap<String, String> hmnum, HashMap<String, String> hmtrans, String folder, String nlpPath, String setu) throws Exception
	{
		String source = Station.getSource(s, hmind);
		String dest = Station.getDestination(s, hmind);
		String atStation = Station.getAtStation(s, hmind);
		String srcTimeInit = Time.getSrcTimeInit(s, hmnum);
		String srcTimeFin = Time.getSrcTimeFin(s, hmnum);
		String newTime[] = CheckAgleGhante(s, srcTimeInit, srcTimeFin);
		srcTimeInit = newTime[0];
		srcTimeFin = newTime[1];
		
		srcTimeInit =  srcTimeInit == null?"00:00":srcTimeInit;
		srcTimeFin =  srcTimeFin == null?"23:59":srcTimeFin;
		String destTimeInit = Time.getDestTimeInit(s);
		destTimeInit =  destTimeInit == null?"00:00":destTimeInit;
		String destTimeFin = Time.getDestTimeFin(s);
		destTimeFin =  destTimeFin == null?"23:59":destTimeFin;
	
		String info = "dep";
		Pattern pattern1 = Pattern.compile("(जाएगी|जा|पहुँच|(जा|जानी|जा रही|जाने वाली) (है|थी)|जाना (है|था)|हो है)");
		Pattern pattern2 = Pattern.compile("(आएगी|आ|(आ|आनी|आ रही|आने वाली) (है|थी)|आना (है|था)|हो है)");
		Matcher matcher1 = pattern1.matcher(s);
		Matcher matcher2 = pattern2.matcher(s);
		if(matcher1.find())
			info = "dep";
		else if(matcher2.find())
			info = "arr";
		String set = Set.getSet(s);
	
		ManageQuery.postProcess(m, dm, s, directMap, hmpll, hmind, hmnum, hmtrans, folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info, nlpPath, setu);
	}

	private static String[] CheckAgleGhante(String s, String srcTimeInit, String srcTimeFin) throws ParseException {
		if(s.contains("अगला") && (s.contains("घंटा") || s.contains("मिनिट") || s.contains("मिनट"))) {
			Pattern pattern = Pattern.compile("(अगला (.*) (घंटा|मिनिट|मिनट))");
			Matcher matcher = pattern.matcher(s);
			if(matcher.find()) {
				String found = matcher.group(2);
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(sdf.format(new Date())));
				srcTimeInit = sdf.format(c.getTime());
				if(s.contains("घंटा"))
					c.add(Calendar.HOUR, Integer.parseInt(found));
				else
					c.add(Calendar.MINUTE, Integer.parseInt(found));
				srcTimeFin = sdf.format(c.getTime());
			}
		}
		String arr[] = {srcTimeInit, srcTimeFin};
		return arr;
	}

}
