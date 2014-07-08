/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.Template;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The Class Strings.
 */
public class Strings {
	
	/** The Constant BUNDLE_NAME. */
	private static final String BUNDLE_NAME = "com.Template.strings"; //$NON-NLS-1$

	/** The Constant RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	/**
	 * Instantiates a new messages.
	 */
	private Strings() {
	}

	/**
	 * Gets the string.
	 * 
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
