package com.SSFHandler;


/*
 * SSFProgram.java
 *
 * Created on December 17, 2007, 6:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


/**
 *
 * @author expert
 */
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * import Sanchay packages and classes
 */
import sanchay.corpus.ssf.SSFProperties;
import sanchay.corpus.ssf.SSFSentence;
import sanchay.corpus.ssf.SSFStory;
import sanchay.corpus.ssf.features.FeatureStructure;
import sanchay.corpus.ssf.features.FeatureStructures;
import sanchay.corpus.ssf.features.impl.FSProperties;
import sanchay.corpus.ssf.features.impl.FeatureStructuresImpl;
import sanchay.corpus.ssf.impl.SSFStoryImpl;
import sanchay.corpus.ssf.tree.SSFNode;
public class SSFProgram {
		/** Creates a new instance of SSFProgram */
	public SSFProgram() {
	}
	public static void main(String args[]){
		SSFProgram sam = new SSFProgram();
		// Create a object of FSPropeties for featureStructure  properties
		FSProperties fsp = new FSProperties();
		// Create Object of SSFPropertes for SSF propertiese.
		SSFProperties ssfp = new SSFProperties();
		SSFProperties cmlp = new SSFProperties();
		SSFStory story = new SSFStoryImpl();

		//Create of Object of SSFSentence interface
		//SSFSentence sentence = new SSFSentenceImpl();
		try {
			//
			//Read FeatureStructure propertes files
			//            fsp.read("/home/expert/arun/props/fs-mandatory-attribs.txt",
			//                    "/home/expert/arun/props/fs-props.txt", "UTF-8"); //throws java.io.FileNotFoundException;
			//            //Read SSF properties file.
			//            ssfp.read("/home/expert/arun/props/ssf-props.txt", "UTF-8"); //throws java.io.FileNotFoundException;
			//            cmlp.read("/home/expert/arun/props/cml-props.txt", "UTF-8"); //throws java.io.FileNotFoundException;
			fsp.read("/home/shreshtha/workspace/ssf-javaapi/Sanchay/props/fs-mandatory-attribs.txt",
					"/home/shreshtha/workspace/ssf-javaapi/Sanchay/props/fs-props.txt", "UTF-8"); //throws java.io.FileNotFoundException;
			//Read SSF properties file.
			ssfp.read("/home/shreshtha/workspace/ssf-javaapi/Sanchay/props/ssf-props.txt", "UTF-8"); //throws java.io.FileNotFoundException;
			//Read CML Properties file	
			cmlp.read("/home/shreshtha/workspace/ssf-javaapi/Sanchay/props/cml-props.txt", "UTF-8"); //throws java.io.FileNotFoundException;
			//Set properties files
			FeatureStructuresImpl.setFSProperties(fsp);
			SSFNode.setSSFProperties(ssfp);
			SSFNode.setCMLProperties(cmlp);

			//Read the input file which is valid SSF format.if it is not then it is use as RAW data
			story.readFile(args[0]);
			//  story.readFile("tokenizer.out");
			//story.readFile("in.txt");
			// story.readFile("check.wx");
			//   story.readFile("tt.txt");
			// story.readFile("D:\\Sanchay-0.2\\Sanchay\\token.tmp");
			//  story.readFile("prune.out");
			//      story.readFile("token.tmp");
			sam.linkStory(story);
			story.save("output.txt", "UTF-8");
			//  sentence.readFile("D:\\Sanchay-0.2\\Sanchay\\test.txt");
			//Read input file that is in SSF format and contain sentence level SSF
			//sentence.readFile("D:\\Sanchay-0.2\\Sanchay\\postagger.out");
			//  sentence.readFile("D:\\Sanchay-0.2\\Sanchay\\token.tmp");
			//story.readFile("/home/sanchay/Sanchay/LinkIn.txt"); //throws java.io.FileNotFoundException;
			//Call the linkSen function which do some processing on sentence
			//   sam.linkSen(sentence);
			// print the output in SSF format on Console
			//  sentence.print(System.out);
			story.print(System.out);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String[] call(String path, String file) throws FileNotFoundException, IOException, Exception
	{
		SSFProgram sam = new SSFProgram();
		FSProperties fsp = new FSProperties();
		SSFProperties ssfp = new SSFProperties();
		SSFProperties cmlp = new SSFProperties();
		SSFStory story = new SSFStoryImpl();
		//SSFSentence sentence = new SSFSentenceImpl();
		fsp.read(path + "ILMT/Sanchay/props/fs-mandatory-attribs.txt",
				path + "ILMT/Sanchay/props/fs-props.txt", "UTF-8");
		ssfp.read(path + "ILMT/Sanchay/props/ssf-props.txt", "UTF-8");
		cmlp.read(path + "ILMT/Sanchay/props/cml-props.txt", "UTF-8");
		FeatureStructuresImpl.setFSProperties(fsp);
		SSFNode.setSSFProperties(ssfp);
		SSFNode.setCMLProperties(cmlp);
		story.readFile(file);
		return sam.linkStory(story); 
	}

	public String[] linkStory(SSFStory story){
		String head = "", root = "", karaka = "";
		int count = story.countSentences();
		for(int i=0;i<count;i++){
			SSFSentence sent = story.getSentence(i);
			int ccount = sent.getRoot().getChildCount();
			for(int j=0;j<ccount;j++){
				SSFNode node = sent.getRoot().getChild(j);
				if (node.getFeatureStructures() != null && node.getChildCount()!= 0){
					FeatureStructure fs = node.getFeatureStructures().getAltFSValue(0);
					int index=fs.getAttributeNames().indexOf("drel");
					if(index==-1){
						root=fs.getAttributeValues().get(fs.getAttributeNames().indexOf("root")).toString();
						root = root.substring(1, root.length()-1);
					}
					else{
						String kr= fs.getAttributeValues().get(fs.getAttributeNames().indexOf("drel")).toString();
						kr=kr.substring(1,3);
						if(kr.equals("k1") || kr.equals("k2")){
							karaka=kr;
							head= fs.getAttributeValues().get(fs.getAttributeNames().indexOf("head")).toString();
							head = head.substring(1, head.length()-1);
						}
					}
				} 
				else {
					System.out.println("enter new feature structure");
					String test = "<fs af='bahuwa,n,m,s,,0,,'>|<fs af='bahuwa,n,m,s,,0,,'>";
					FeatureStructures fsi = new FeatureStructuresImpl();
					try{
						fsi.readString(test);
						node.setFeatureStructures(fsi);
					} catch(Exception e){}
					System.out.println("no FeatureStructure");
				}
			}
		}
		String[] arr={karaka,head,root};
		return arr;
	}
}
