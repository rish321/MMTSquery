package com.qureyprocess.components.action.semanticroles;

import java.io.IOException;

import com.queryprocess.components.Query;

public class Theme {
	public static String getTheme(String folder, String foldertmp, String string) throws IOException, InterruptedException {
		String file = "findTheme"; 
		String params = folder + file + ".sh ";
		params += string;
		return Query.makeQuery(foldertmp, file, params);
	}
}
