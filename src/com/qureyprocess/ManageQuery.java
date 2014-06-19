package com.qureyprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dag.DAG;
import com.dialogmanager.Dialog;
import com.hp.hpl.jena.ontology.OntModel;
import com.ontologicalMapping.ReplaceParallel;
import com.qureyprocess.components.Coach;
import com.qureyprocess.components.Count;
import com.qureyprocess.components.Duration;
import com.qureyprocess.components.Instance;
import com.qureyprocess.components.Station;
import com.qureyprocess.components.Time;

public class ManageQuery {


	public static void postProcess(OntModel m, Dialog dm, String s,
			HashMap<String, String> directMap, HashMap<String, String> hmpll, HashMap<String, String> hmind, HashMap<String, String> hmnum,
			HashMap<String, String> hmtrans, String folder, String source, String dest, String atStation,
			String srcTimeInit, String srcTimeFin, String destTimeInit, String destTimeFin,
			String set, String info, String nlpPath, String setu) throws Exception {
		if(s.contains("कितना कितना देर") || s.contains("कितनी कितनी देर") || s.contains("कितना कितना बज")
				|| s.contains("कितना कितना समय") || s.contains("कितना कितना समय")) {
			if(s.contains("कितना कितना देर") || s.contains("कितनी कितनी देर")
					|| s.contains("कितना कितना समय") || s.contains("कितना कितना समय")) {
				if(source == null)
					source = hmind.get(dm.requestResponse(hmtrans, "Incomplete arguments: Please specify source station"));
				if(dest == null)
					dest = hmind.get(dm.requestResponse(hmtrans, "Incomplete arguments: Please specify destination station"));
				if(atStation == null) {
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + "jane wali gaadi itni-itni der mein pahunchti hain");
					ProcessAnswer.printAnswer(hmtrans, Duration.getDurationList(folder, source, dest, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin), m);
				}
				else {
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + atStation + " par " + srcTimeInit + " se " + srcTimeFin + " ke beech " + "gaadiyan itni-itni der mein pahunchti hain");
					ProcessAnswer.printAnswer(hmtrans, Duration.getDurationList(folder, source, dest, atStation, srcTimeInit, srcTimeFin), m);
				}
			}
			else if(s.contains("कितना कितना बज")) {
				if(source == null)
					source = dm.requestResponse(hmtrans, "Incomplete arguments: Please specify source station");
				if(dest == null)
					dest = dm.requestResponse(hmtrans, "Incomplete arguments: Please specify destination station");
				ProcessAnswer.printAnswer(hmtrans, Time.getTimeList(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
			}
		}
		else if(s.contains("कितनी कितनी") || s.contains("कितना कितना")
				|| s.contains("कहाँ कहाँ") || s.contains("कब कब") ||  s.contains("कौन कौन")) {
			if(source == null)
				source = dm.requestResponse(hmtrans, "Incomplete arguments: Please specify source station");
			if(dest == null)
				dest = dm.requestResponse(hmtrans, "Incomplete arguments: Please specify destination station");
			if(s.contains("कितनी कितनी") || s.contains("कितना कितना")) {
				Pattern pattern = Pattern.compile("(कितनी कितनी|कितना कितना) ");
				Matcher matcher = pattern.matcher(s);
				if(matcher.find()) {
					String found = matcher.group();
					//System.out.println(found);
					//System.out.println(s.indexOf(found) + found.length() + " " + s.indexOf(" ", s.indexOf(found)+found.length()+1));
					found = s.substring(s.indexOf(found) + found.length(), s.indexOf(" ", s.indexOf(found)+found.length()+1));
					found = ReplaceParallel.replaceParallel(found);
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + "itne itne " + found + " " + info + " hain");
					ProcessAnswer.printAnswer(hmtrans, Count.getCount(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info, found), m);
				}
			}
			else if(s.contains("कब कब")) {
				ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + "gaadi itne-itne baje " + info + " hai");
				ProcessAnswer.printAnswer(hmtrans, Time.getTimeList(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
			}
			else if(s.contains("कहाँ कहाँ")) {
				if(s.contains("कहाँ कहाँ से"))
					ProcessAnswer.printAnswer(hmtrans, Station.getIntermediateStation(folder, source, dest, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
				else if(s.contains("कहाँ कहाँ तक")) {
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + set + " gaadi yahan se " + info + " hai");
					if(info.equals("arr"))
						ProcessAnswer.translate(hmtrans, dest);
					else {
						ArrayList<String> arr = ProcessAnswer.getAnswer(Station.getStation(folder, source, dest, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
						for(int i = 0; i < arr.size(); i++)
							ProcessAnswer.translate(hmtrans, arr.get(i).split("-")[1]);
					}
				}
				else {
					if(info.equals("arr")) {
						ArrayList<String> arr = ProcessAnswer.getAnswer(Station.getStation(folder, source, dest, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
						for(int i = 0; i < arr.size(); i++)
							source = arr.get(i).split("-")[0];
					}
					else {
						ArrayList<String> arr = ProcessAnswer.getAnswer(Station.getStation(folder, source, dest, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
						for(int i = 0; i < arr.size(); i++)
							dest = arr.get(i).split("-")[1];
					}
					ProcessAnswer.printAnswer(hmtrans, Station.getIntermediateStation(folder, source, dest, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
				}
			}
			else if(s.contains("कौन कौन")) {
				Pattern pattern = Pattern.compile("कौन कौन( से|) ");
				Matcher matcher = pattern.matcher(s);
				while(matcher.find()) {
					String found = matcher.group();
					//System.out.println(found);
					//System.out.println(s.indexOf(found) + found.length() + " " + s.indexOf(" ", s.indexOf(found)+found.length()+1));
					found = s.substring(s.indexOf(found) + found.length(), s.indexOf(" ", s.indexOf(found)+found.length()+1));
					//System.out.println(found);
					found = ReplaceParallel.replaceParallel(found);
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + "itne " + found + " " + info + " hain");
					ProcessAnswer.printAnswer(hmtrans, Count.getCount(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info, found), m);
				}
			}
		}
		else if(s.contains("कितना देर") || s.contains("कितनी देर") || s.contains("कितना बज")
				|| s.contains("कितना समय") || s.contains("कितना समय")) {
			if(source == null)
				source = dm.requestResponse(hmtrans, "Incomplete arguments: Please specify source station");
			if(dest == null)
				dest = dm.requestResponse(hmtrans, "Incomplete arguments: Please specify destination station");
			if(s.contains("कितना देर") || s.contains("कितनी देर")) {
				if(source == null)
					ProcessAnswer.translate(hmtrans, "Incomplete arguments: Please specify source station");
				if(dest == null)
					ProcessAnswer.translate(hmtrans, "Incomplete arguments: Please specify destination station");
				if(atStation == null) {
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + set + " jane wali gaadi itni der mein pahunchti hai");
					ProcessAnswer.printAnswer(hmtrans, Duration.getDuration(folder, source, dest, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set), m);
				}
				else {
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + atStation + " par " + srcTimeInit + " se " + srcTimeFin + " ke beech " + set+ " gaadiyan itni der mein pahunchti hai");
					ProcessAnswer.printAnswer(hmtrans, Duration.getDuration(folder, source, dest, atStation, srcTimeInit, srcTimeFin, set), m);
				}
			}
			else if(s.contains("कितना बज")) {
				ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + set + " gaadi itne baje " + info + " hai");
				ProcessAnswer.printAnswer(hmtrans, Time.getTime(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
			}
		}
		else if(s.contains("कितनी") || s.contains("कितना")
				|| s.contains("कब") || s.contains("कहाँ") || s.contains("कौन")) {
			if(source == null)
				source = dm.requestResponse(hmtrans, "Incomplete arguments: Please specify source station");
			if(dest == null)
				dest = dm.requestResponse(hmtrans, "Incomplete arguments: Please specify destination station");
			if(s.contains("कितनी") || s.contains("कितना")) {
				Pattern pattern = Pattern.compile("(कितनी|कितना) ");
				Matcher matcher = pattern.matcher(s);
				if(matcher.find()) {
					String found = matcher.group();
					//System.out.println(found);
					//System.out.println(s.indexOf(found) + found.length() + " " + s.indexOf(" ", s.indexOf(found)+found.length()+1));
					found = s.substring(s.indexOf(found) + found.length(), s.indexOf(" ", s.indexOf(found)+found.length()+1));
					found = ReplaceParallel.replaceParallel(found);
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + "itne " + found + " " + info + " hain");
					ProcessAnswer.printAnswer(hmtrans, Count.getCount(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info, found), m);
				}
			}
			else if(s.contains("कब")) {
				ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + set + " gaadi itne baje " + info + " hai");
				ProcessAnswer.printAnswer(hmtrans, Time.getTime(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
			}
			else if(s.contains("कहाँ")) {
				if(s.contains("कहाँ से")) {
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + set + " gaadi yahan se " + info + " hai");
					if(info.equals("dep"))
						ProcessAnswer.translate(hmtrans, source);
					else {
						ArrayList<String> arr = ProcessAnswer.getAnswer(Station.getStation(folder, source, dest, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
						for(int i = 0; i < arr.size(); i++)
							ProcessAnswer.translate(hmtrans, arr.get(i).split("-")[0]);
					}
				}
				else if(s.contains("कहाँ तक")) {
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + set + " gaadi yahan se " + info + " hai");
					if(info.equals("arr"))
						ProcessAnswer.translate(hmtrans, dest);
					else {
						ArrayList<String> arr = ProcessAnswer.getAnswer(Station.getStation(folder, source, dest, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
						for(int i = 0; i < arr.size(); i++)
							ProcessAnswer.translate(hmtrans, arr.get(i).split("-")[1]);
					}
				}
				else {

				}
			}
			else if(s.contains("कौन")) {
				Pattern pattern = Pattern.compile("कौन(| से)");
				Matcher matcher = pattern.matcher(s);
				while(matcher.find()) {
					String found = matcher.group();
					if(found.endsWith("से"))
						continue;
					//System.out.println(found);
					//System.out.println(s.indexOf(found) + found.length() + " " + s.indexOf(" ", s.indexOf(found)+found.length()+1));
					found = s.substring(s.indexOf(found) + found.length(), s.indexOf(" ", s.indexOf(found)+found.length()+1));
					found = ReplaceParallel.replaceParallel(found);
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + "itne " + found + " " + info + " hain");
					//ProcessAnswer.printAnswer(Count.getCount(source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info, found));
				}
			}
		}
		else if(s.contains("कैसे कैसे") || s.contains("कैसे") || s.contains("क्यूँ") || s.contains("क्यूँ क्यूँ")) {
			List<String> acts = Instance.extractInstanceActionTheme(m, s, directMap, folder, nlpPath, setu);
			String action = acts.get(0);
			DAG dag = new DAG();
			dag.fillDAGAction(folder, action, m);
			ArrayList<String> topo = dag.topologicalsort();
			if((s.contains("क्यूँ") || s.contains("क्यूँ क्यूँ")) && s.contains("नहीं")) {
				DAG.totalOrder(folder, m, dag, topo);
			}
			else if(s.contains("कैसे कैसे") || s.contains("कैसे")) {
				ProcessAnswer.translate(hmtrans, "Ye saare kadam uthane se ticket kharid sakte hain");
				DAG.actionOrder(folder, m, dag, topo);
			}
		}
		else if(s.contains("क्या")) {
			String in = dm.requestResponse(hmtrans, "Intention unclear (multiple senses): Boolean answer(1)/Other answer(2)");
			if(in.equals("1")) {
				//फलकनुमा से लिंगमपल्ली की 6:00 बजे से 9:00 बजे के बीच पहली ट्रैन में महिला कोच है क्या?
				if(s.contains("कोच") || s.contains("डब्बा") ||s.contains("बोगी") ){
					String coachType = Coach.extractCoachType(s, directMap);
					System.out.println(coachType);
					if(ProcessAnswer.getAnswer(Coach.getCoach(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info, coachType), m).contains(coachType))
						dm.informUser(hmtrans, "yes");
					else 
						dm.informUser(hmtrans, "no");
				}
				//Q1. लिंगमपल्ली से भरतनगर के बीच क्या कोई ट्रेन है?
				//लिंगमपल्ली से भरतनगर की अगली ट्रेन क्या हैदराबाद पर रुकती है?
				else
				{
					ArrayList<String> arr = ProcessAnswer.getAnswer(Time.getTime(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
					if(arr.size()==0)
						dm.informUser(hmtrans, "no");
					else 
						dm.informUser(hmtrans, "yes");
				}
			}
			else if(in.equals("2")) {
				//Q1. लिंगमपल्ली से भरतनगर के बीच क्या कोई ट्रेन है?
				//लिंगमपल्ली से भरतनगर की अगली ट्रेन क्या हैदराबाद पर रुकती है?
				{
					ProcessAnswer.translate(hmtrans, source + " se " + dest + " tak " + srcTimeInit + " se " + srcTimeFin + " ke beech " + set + " gaadi itne baje " + info + " hai");
					ProcessAnswer.printAnswer(hmtrans, Time.getTime(folder, source, dest, atStation, srcTimeInit, srcTimeFin, destTimeInit, destTimeFin, set, info), m);
				}
				//क्या लिंगमपल्ली से भरतनगर को जाने वाली अगली ट्रेन से हम 10 बजे तक फलकनुमा पहुच पाएँगे?
				//how to fetch detTime?
			}
		}
		else{
			dm.informUser(hmtrans, "Not understandable: Question word and type unclear.");
		}
	}
}

