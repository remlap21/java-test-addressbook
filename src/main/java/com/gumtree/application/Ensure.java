package com.gumtree.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Assert class for standard assertions
 */
public abstract class Ensure {

	private static final Logger logger = LoggerFactory
	.getLogger(Ensure.class);
	
	/**
	 * Check the object is not null else throw an
	 * {@link IllegalArgumentException} with the given message
	 * 
	 * @param object
	 * @param message
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			logger.warn( message );
			throw new IllegalArgumentException(message);
		}
	}

}
