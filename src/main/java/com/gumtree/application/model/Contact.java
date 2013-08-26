package com.gumtree.application.model;

import org.joda.time.DateTime;

import com.gumtree.application.Ensure;

public class Contact {

	private String fullName;
	private Gender gender;
	private DateTime dob;
	
	public Contact(String fullName, Gender gender, DateTime dob) {
		Ensure.notNull(fullName, "A contact must have a valid name!");
		
		this.fullName = fullName;
		this.gender = gender;
		this.dob = dob;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @return the dob
	 */
	public DateTime getDob() {
		return dob;
	}

}
