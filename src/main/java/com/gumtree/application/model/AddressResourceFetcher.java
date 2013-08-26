package com.gumtree.application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Given a valid path will attempt to read automobile resources from disk
 */
public class AddressResourceFetcher implements ResourceFetcher {

	 private static final Logger logger =
	 LoggerFactory.getLogger(AddressResourceFetcher.class);

	private final String path;

	@Autowired
	public AddressResourceFetcher(String path) {
		this.path = path;
	}

	/**
	 * return the related Contact resources
	 * 
	 * @return
	 */
	public List<Contact> getAllResources() {

		InputStreamReader reader = null;
		List<Contact> contacts = new ArrayList<Contact>();
		try {
		try {
			reader = new InputStreamReader( getClass().getResourceAsStream( path ) );
			BufferedReader br = new BufferedReader( reader );
			
			String line = br.readLine();

			while (line != null) {
				Contact c = stringToContact( line );
				
				if( c != null )
				{
					contacts.add( c );
				}
				
				line = br.readLine();
			}
			
		} finally {
			reader.close();
		} } catch (IOException ex) {
			logger.warn(ex.getMessage());
		}
		return contacts;
	}

	Contact stringToContact(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
