/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.dialogmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.queryprocess.ProcessAnswer;

/**
 * The Class Dialog.
 */
public class Dialog {

	/**
	 * Request response.
	 * 
	 * @param hmtrans
	 *            the hmtrans
	 * @param display
	 *            the display
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String requestResponse(HashMap<String, String> hmtrans, String display) throws IOException
	{
		System.err.println(display);
		informUser(hmtrans, display);
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    String s = bufferRead.readLine();
	    return s;
	}
	
	public String warnResponse(HashMap<String, String> hmtrans, String display) throws IOException
	{
		warnUser(hmtrans, display);
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    String s = bufferRead.readLine();
	    return s;
	}
	
	public void warnUser(HashMap<String, String> hmtrans, String display)
	{
		ProcessAnswer.translateerr(hmtrans, display);
	}
	
	/**
	 * Inform user.
	 * 
	 * @param hmtrans
	 *            the hmtrans
	 * @param display
	 *            the display
	 */
	public void informUser(HashMap<String, String> hmtrans, String display)
	{
		ProcessAnswer.translate(hmtrans, display);
	}
}
