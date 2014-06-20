package com.queryprocess.components;

import java.io.IOException;

public class Condition {

	public static String getPrecondition(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findPrecondition"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}

	public static String getOutcome(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findOutcome"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}

}
