package com.qureyprocess.components;

import java.io.IOException;

public class Action {

	public static String getAction(String folder, String foldertmp, String action, String theme) throws IOException, InterruptedException {
		String file = "findAction"; 
		String params = folder + file + ".sh ";
		params += action + " " + theme;
		return Query.makeQuery(foldertmp, file, params);
	}

	public static String getSubaction(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findSubaction"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}

	public static String getoutAction(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findOutAction"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}

	public static String getpreAction(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findPreAction"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}
	
}