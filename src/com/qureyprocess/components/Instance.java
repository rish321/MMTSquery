package com.qureyprocess.components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.SSFHandler.SSFProgram;
import com.hp.hpl.jena.ontology.OntModel;
import com.qureyprocess.ProcessAnswer;
import com.sparql.Sparql;

public class Instance {

	public static List<String> extractInstanceActionTheme(OntModel m, String s,
			HashMap<String, String> directMap, String folder, String nlpPath,
			String setu) throws IOException, InterruptedException,
			FileNotFoundException, Exception {
		File file = new File("/tmp/tempin.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(s);
		bw.close();
		Sparql.createSparqlFile(folder + "kyu_kaise.sh /tmp/tempin.txt " + setu + " " + nlpPath+"ILMT/" + " /tmp/tempout.txt");
		String[] krelation= SSFProgram.call(nlpPath, "/tmp/tempout.txt");
		List<String> acts = ProcessAnswer.getAnswer(Action.getAction(folder, directMap.get(krelation[2]), directMap.get(krelation[1])), m);
		return acts;
	}

	
}
