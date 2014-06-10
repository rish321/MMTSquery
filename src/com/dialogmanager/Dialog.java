package com.dialogmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.qureyprocess.ProcessAnswer;

public class Dialog {

	public String requestResponse(HashMap<String, String> hmtrans, String display) throws IOException
	{
		informUser(hmtrans, display);
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    String s = bufferRead.readLine();
	    return s;
	}
	
	public void informUser(HashMap<String, String> hmtrans, String display)
	{
		ProcessAnswer.translate(hmtrans, display);
	}
}
