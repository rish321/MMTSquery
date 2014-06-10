package com.qureyprocess;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dialogmanager.Dialog;
import com.hp.hpl.jena.ontology.OntModel;
import com.qureyprocess.components.Set;
import com.qureyprocess.components.Station;
import com.qureyprocess.components.Time;

public class ManageArguments {

	public static void preProcess(OntModel m, Dialog dm, String s, HashMap<String, String> hmqtype, HashMap<String, String> hmpll, HashMap<String, String> hmind, HashMap<String, String> hmnum, HashMap<String, String> hmtrans, String folder) throws IOException, InterruptedException
	{
		String source = Station.getSource(s, hmind);
		String dest = Station.getDestination(s, hmind);
		String atStation = Station.getAtStation(s, hmind);
		String srcTimeInit = Time.getSrcTimeInit(s, hmnum);
		srcTimeInit =  srcTimeInit == null?"00:00":srcTimeInit;
		String srcTimeFin = Time.getSrcTimeFin(s, hmnum);
		srcTimeFin =  srcTimeFin == null?"23:59":srcTimeFin;
		String destTimeInit = Time.getDestTimeInit(s);
		destTimeInit =  destTimeInit == null?"00:00":destTimeInit;
		String destTimeFin = Time.getDestTimeFin(s);
		destTimeFin =  destTimeFin == null?"23:59":destTimeFin;
	
		String info = "";
		Pattern pattern1 = Pattern.compile("(जाएगी|(जाती|जानी|जा रही|जाने वाली) (है|थी)|जाना (है|था)|होती है)");
		Pattern pattern2 = Pattern.compile("(आएगी|(आती|आनी|आ रही|आने वाली) (है|थी)|आना (है|था)|होती है)");
		Matcher matcher1 = pattern1.matcher(s);
		Matcher matcher2 = pattern2.matcher(s);
		if(matcher1.find())
			info = "dep";
		else if(matcher2.find())
			info = "arr";
		String set = Set.getSet(s);
	
		ManageQuery.postProcess(m, dm, s, hmqtype, hmpll, hmind, hmnum, hmtrans, folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info);
	}

}
