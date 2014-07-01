/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess;

import java.util.HashMap;

import com.dialogmanager.Dialog;
import com.hp.hpl.jena.ontology.OntModel;
import com.queryprocess.components.Info;
import com.queryprocess.components.Set;
import com.queryprocess.components.Station;
import com.queryprocess.components.Time;

/**
 * The Class ManageArguments.
 */
public class ManageArguments {

	/**
	 * Pre process.
	 * 
	 * @param m
	 *            the m
	 * @param dm
	 *            the dm
	 * @param s
	 *            the s
	 * @param directMap
	 *            the direct map
	 * @param hmpll
	 *            the hmpll
	 * @param hmind
	 *            the hmind
	 * @param hmnum
	 *            the hmnum
	 * @param hmtrans
	 *            the hmtrans
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param nlpPath
	 *            the nlp path
	 * @param setu
	 *            the setu
	 * @throws Exception
	 *             the exception
	 */
	public static void preProcess(OntModel m, Dialog dm, String s, HashMap<String, String> directMap, HashMap<String, String> hmpll, HashMap<String, String> hmind, HashMap<String, String> hmnum, HashMap<String, String> hmtrans, String folder, String foldertmp, String nlpPath, String setu) throws Exception
	{
		String source = Station.getSource(s, hmind);
		String dest = Station.getDestination(s, hmind,source);
		String atStation = Station.getAtStation(s, hmind,source,dest);
		String srcTimeInit = Time.getSrcTimeInit(s, hmnum);
		srcTimeInit =  srcTimeInit == null?Strings.getString("startDayTime"):srcTimeInit; //$NON-NLS-1$
		String srcTimeFin = Time.getSrcTimeFin(s, hmnum);
		srcTimeFin =  srcTimeFin == null?Strings.getString("endDayTime"):srcTimeFin; //$NON-NLS-1$
		String destTimeInit = Time.getDestTimeInit(s);
		destTimeInit =  destTimeInit == null?Strings.getString("startDayTime"):destTimeInit; //$NON-NLS-1$
		String destTimeFin = Time.getDestTimeFin(s);
		destTimeFin =  destTimeFin == null?Strings.getString("endDayTime"):destTimeFin; //$NON-NLS-1$
		String info = Info.getInfo(s);
		String set = Set.getSet(s);
		ManageQuery.postProcess(m, dm, s, directMap, hmpll, hmind, hmnum, hmtrans, folder, foldertmp, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info, nlpPath, setu);
	}

}
