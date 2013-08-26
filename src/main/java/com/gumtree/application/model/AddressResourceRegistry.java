package com.gumtree.application.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public class AddressResourceRegistry {

	private ResourceFetcher fetcher;
	private List<Contact> contacts;
	
	@Autowired
	public AddressResourceRegistry( ResourceFetcher fetcher ){
		this.fetcher = fetcher;
		this.contacts = fetcher.getAllResources();
	}
	
	/**
	 * Return a list of contacts that match the supplied contact filter
	 * 
	 * @param filterContact
	 */
	public List<Contact> getContacts(ContactFilter... filterContact) {
		
		// TODO: Apply filters to list and return
		return null;
	}

}
