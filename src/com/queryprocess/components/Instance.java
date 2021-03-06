/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.queryprocess.components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.SSFHandler.SSFProgram;
import com.hp.hpl.jena.ontology.OntModel;
import com.queryprocess.ProcessAnswer;
import com.sparql.Sparql;

/**
 * The Class Instance.
 */
public class Instance {

	/**
	 * Extract instance action theme.
	 * 
	 * @param m
	 *            the m
	 * @param s
	 *            the s
	 * @param directMap
	 *            the direct map
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param nlpPath
	 *            the nlp path
	 * @param setu
	 *            the setu
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws Exception
	 *             the exception
	 */
	public static List<String> extractInstanceActionTheme(OntModel m, String s,
			HashMap<String, String> directMap, String folder, String foldertmp, String nlpPath,
			String setu) throws IOException, InterruptedException,
			FileNotFoundException, Exception {
		File file = new File("/tmp/tempin.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(s);
		bw.close();
		Sparql.createSparqlFile(folder + "kyu_kaise.sh /tmp/tempin.txt " + setu + " " + nlpPath+"ILMT/" + " /tmp/tempout.txt");
		String[] krelation= SSFProgram.call(nlpPath, "/tmp/tempout.txt");
		List<String> acts = ProcessAnswer.getAnswer(Action.getAction(folder, foldertmp, directMap.get(krelation[2]), directMap.get(krelation[1])), m);
		return acts;
	}

	
}
