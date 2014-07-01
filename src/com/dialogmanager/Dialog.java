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
		informUser(hmtrans, display);
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    String s = bufferRead.readLine();
	    return s;
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
