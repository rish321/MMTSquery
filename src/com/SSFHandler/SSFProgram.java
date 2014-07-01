/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
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

/**
 * The Class SSFProgram.
 */
public class SSFProgram {
		
	/**
	 * Call.
	 * 
	 * @param path
	 *            the path
	 * @param file
	 *            the file
	 * @return the string[]
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws Exception
	 *             the exception
	 */
	public static String[] call(String path, String file) throws FileNotFoundException, IOException, Exception
	{
		SSFProgram sam = new SSFProgram();
		FSProperties fsp = new FSProperties();
		SSFProperties ssfp = new SSFProperties();
		SSFProperties cmlp = new SSFProperties();
		SSFStory story = new SSFStoryImpl();
		//SSFSentence sentence = new SSFSentenceImpl();
		fsp.read(path + Strings.getString("attributeFile"), //$NON-NLS-1$
				path + Strings.getString("propertyFile"), Strings.getString("extn")); //$NON-NLS-1$ //$NON-NLS-2$
		ssfp.read(path + Strings.getString("ssfPropsFile"), Strings.getString("extn")); //$NON-NLS-1$ //$NON-NLS-2$
		cmlp.read(path + Strings.getString("cmlPropsFile"), Strings.getString("extn")); //$NON-NLS-1$ //$NON-NLS-2$
		FeatureStructuresImpl.setFSProperties(fsp);
		SSFNode.setSSFProperties(ssfp);
		SSFNode.setCMLProperties(cmlp);
		story.readFile(file);
		return sam.linkStory(story); 
	}

	/**
	 * Link story.
	 * 
	 * @param story
	 *            the story
	 * @return the string[]
	 */
	public String[] linkStory(SSFStory story){
		String head = "", root = "", karaka = ""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		int count = story.countSentences();
		for(int i=0;i<count;i++){
			SSFSentence sent = story.getSentence(i);
			int ccount = sent.getRoot().getChildCount();
			for(int j=0;j<ccount;j++){
				SSFNode node = sent.getRoot().getChild(j);
				if (node.getFeatureStructures() != null && node.getChildCount()!= 0){
					FeatureStructure fs = node.getFeatureStructures().getAltFSValue(0);
					int index=fs.getAttributeNames().indexOf("drel"); //$NON-NLS-1$
					if(index==-1){
						root=fs.getAttributeValues().get(fs.getAttributeNames().indexOf("root")).toString(); //$NON-NLS-1$
						root = root.substring(1, root.length()-1);
					}
					else{
						String kr= fs.getAttributeValues().get(fs.getAttributeNames().indexOf("drel")).toString(); //$NON-NLS-1$
						kr=kr.substring(1,3);
						if(kr.equals("k1") || kr.equals("k2")){ //$NON-NLS-1$ //$NON-NLS-2$
							karaka=kr;
							head= fs.getAttributeValues().get(fs.getAttributeNames().indexOf("head")).toString(); //$NON-NLS-1$
							head = head.substring(1, head.length()-1);
						}
					}
				} 
				else {
					System.out.println("enter new feature structure"); //$NON-NLS-1$
					String test = "<fs af='bahuwa,n,m,s,,0,,'>|<fs af='bahuwa,n,m,s,,0,,'>"; //$NON-NLS-1$
					FeatureStructures fsi = new FeatureStructuresImpl();
					try{
						fsi.readString(test);
						node.setFeatureStructures(fsi);
					} catch(Exception e){}
					System.out.println("no FeatureStructure"); //$NON-NLS-1$
				}
			}
		}
		String[] arr={karaka,head,root};
		return arr;
	}
}
