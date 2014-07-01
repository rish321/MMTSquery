/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.dag;

import java.util.HashSet;
import java.util.Set;

/**
 * The Class Struct.
 */
public class Struct {
	
	/** The color. */
	int color;
	
	/** The prev. */
	String prev;
	
	/** The i time. */
	int iTime;
	
	/** The time. */
	int fTime;
	
	/** The clas. */
	String clas;
	
	/** The set. */
	Set<String> set;
	
	/**
	 * Instantiates a new struct.
	 */
	Struct()
	{
		iTime = -1;
		color = 0;
		prev = ""; //$NON-NLS-1$
		set = new HashSet<String>();
	}
}
