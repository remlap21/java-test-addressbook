package com.gumtree.application.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

/**
 * Reads a given input file
 */
public class AddressResourcesFetcherTest {

	@Test
	public void read_should_return_valid_list() throws IOException {
		AddressResourceFetcher oir = new AddressResourceFetcher("/AddressBook");
		
		List<Contact> contacts = oir.getAllResources();
		
		assertEquals(5, contacts.size() );
	}

	@Test
	public void stringToContact_should_return_null()
	{
		AddressResourceFetcher oir = new AddressResourceFetcher("/AddressBook");
		Contact c = oir.stringToContact("SOME LINE");
		assertNull( c );
	}
}
