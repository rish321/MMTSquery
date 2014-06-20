package com.queryprocess.components;

public class Set {

	public static String getSet(String s) {
		String set = "MIN";
		if(s.contains("पहली") || s.contains("अगली"))
			set = "MIN";
		if(s.contains("आखरी") || s.contains("आखिरी") || s.contains("पिछली"))
			set = "MAX";
		return set;
	}

}
