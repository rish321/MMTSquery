package com.dag;

import java.util.HashSet;
import java.util.Set;

public class Struct {
	int color;
	String prev;
	int iTime;
	int fTime;
	String clas;
	Set<String> set;
	Struct()
	{
		iTime = -1;
		color = 0;
		prev = "";
		set = new HashSet<String>();
	}
}
