package com.gumtree.application.model;

import java.util.List;

import com.gumtree.application.Ensure;

/**
 * A wrapper object for a list of contacts
 */
public class ContactList {

	private List<Contact> contacts;

	public ContactList(List<Contact> contacts) {
		Ensure.notNull(contacts, "Must provide a list of contacts");
		this.contacts = contacts;
	}

	/**
	 * @return the contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}

}
