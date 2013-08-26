package com.gumtree.application.model;

import java.util.List;


public interface ResourceFetcher {

	
	/**
	 * Should return all resources available to the fetcher instance
	 * 
	 * @param id
	 * @return
	 */
	List<Contact> getAllResources();
	
}
