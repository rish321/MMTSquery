package com.ontologicalMapping;

public class ReplaceParallel {

	public static String replaceParallel(String found) {
		if(found.equals("ट्रेन") || found.equals("ट्रेने"))
			found = "ofTrain";
		if(found == "स्टेशन")
			found = "atStation";
		if(found.equals("दिन"))
			found = "runs";
		return found;
	}

}
