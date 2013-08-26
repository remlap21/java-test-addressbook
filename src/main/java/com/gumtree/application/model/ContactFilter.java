package com.gumtree.application.model;

import com.gumtree.application.Ensure;

public class ContactFilter {
	private ContactFilterOptions filter;
	private String value;
	
	public ContactFilter(ContactFilterOptions filter, String value) {
		Ensure.notNull(filter, "Filter cannot be null!");
		this.filter = filter;
		this.value = value;
	}

	/**
	 * @return the filter
	 */
	public ContactFilterOptions getFilter() {
		return filter;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	
}
