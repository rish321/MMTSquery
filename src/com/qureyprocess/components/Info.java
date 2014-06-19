package com.qureyprocess.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Info {

	public static String getInfo(String s) {
		String info = "dep";
		Pattern pattern1 = Pattern.compile("(जाएगी|जा|पहुँच|(जा|जानी|जा रही|जाने वाली) (है|थी)|जाना (है|था)|हो है)");
		Pattern pattern2 = Pattern.compile("(आएगी|आ|(आ|आनी|आ रही|आने वाली) (है|थी)|आना (है|था)|हो है)");
		Matcher matcher1 = pattern1.matcher(s);
		Matcher matcher2 = pattern2.matcher(s);
		if(matcher1.find())
			info = "dep";
		else if(matcher2.find())
			info = "arr";
		return info;
	}

}
