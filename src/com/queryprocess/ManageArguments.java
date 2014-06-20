package com.queryprocess;

import java.util.HashMap;

import com.dialogmanager.Dialog;
import com.hp.hpl.jena.ontology.OntModel;
import com.queryprocess.components.Info;
import com.queryprocess.components.Set;
import com.queryprocess.components.Station;
import com.queryprocess.components.Time;

public class ManageArguments {

	public static void preProcess(OntModel m, Dialog dm, String s, HashMap<String, String> directMap, HashMap<String, String> hmpll, HashMap<String, String> hmind, HashMap<String, String> hmnum, HashMap<String, String> hmtrans, String folder, String foldertmp, String nlpPath, String setu) throws Exception
	{
		String source = Station.getSource(s, hmind);
		String dest = Station.getDestination(s, hmind,source);
		String atStation = Station.getAtStation(s, hmind,source,dest);
		String srcTimeInit = Time.getSrcTimeInit(s, hmnum);
		srcTimeInit =  srcTimeInit == null?"00:00":srcTimeInit;
		String srcTimeFin = Time.getSrcTimeFin(s, hmnum);
		srcTimeFin =  srcTimeFin == null?"23:59":srcTimeFin;
		String destTimeInit = Time.getDestTimeInit(s);
		destTimeInit =  destTimeInit == null?"00:00":destTimeInit;
		String destTimeFin = Time.getDestTimeFin(s);
		destTimeFin =  destTimeFin == null?"23:59":destTimeFin;
		String info = Info.getInfo(s);
		String set = Set.getSet(s);
		ManageQuery.postProcess(m, dm, s, directMap, hmpll, hmind, hmnum, hmtrans, folder, foldertmp, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info, nlpPath, setu);
	}

}
