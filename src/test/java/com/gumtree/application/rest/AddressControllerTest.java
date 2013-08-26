package com.gumtree.application.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.gumtree.application.model.AddressResourceRegistry;
import com.gumtree.application.model.Contact;
import com.gumtree.application.model.ContactFilter;
import com.gumtree.application.model.ContactFilterOptions;
import com.gumtree.application.model.ContactList;

public class AddressControllerTest {

	private AddressResourceRegistry registry;
	private AddressController controller;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void before() {
		registry = mock(AddressResourceRegistry.class);
		controller = new AddressController(registry);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	/**
	 * Count entries - filter
	 */
	@Test
	public void countEntries_filter_males_only() {
		request.addParameter("gender", "male");

		controller.getContactCount(request, response);

		ArgumentCaptor<ContactFilter> argCap = ArgumentCaptor
				.forClass(ContactFilter.class);
		verify(registry).getContacts(argCap.capture());

		assertEquals(ContactFilterOptions.GENDER, argCap.getValue().getFilter());
		assertEquals("MALE", argCap.getValue().getValue());
	}

	/**
	 * Count entries - no filter
	 */
	@Test
	public void countEntries_no_filter() {
		controller.getContactCount(request, response);

		ArgumentCaptor<ContactFilter[]> argCap = ArgumentCaptor
				.forClass(ContactFilter[].class);
		verify(registry).getContacts(argCap.capture());

		assertEquals(0,argCap.getValue().length);
	}
	
	/**
	 * Count entries - 0
	 */
	@Test
	public void countEntries_should_return_0()
	{
		when( registry.getContacts( any( ContactFilter.class ) ) ).thenReturn( new ArrayList() );
		int result = controller.getContactCount(request, response);
		
		assertEquals(0, result);
	}
	
	/**
	 * Count entries - 2
	 */
	@Test
	public void countEntries_should_return_2()
	{
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(mock(Contact.class) );
		contacts.add(mock(Contact.class) );
		when( registry.getContacts( any( ContactFilter.class ) ) ).thenReturn( contacts );
		int result = controller.getContactCount(request, response);
		
		assertEquals(2, result);
	}

	/**
	 * Get entries - no filter
	 */
	@Test
	public void getContacts_no_filter()
	{
		controller.getContacts(request, response);
		
		ArgumentCaptor<ContactFilter[]> argCap = ArgumentCaptor.forClass(ContactFilter[].class);
		verify( registry ).getContacts( argCap.capture() );
		
		assertEquals(0, argCap.getValue().length);
	}
	
	/**
	 * Get entries - 0
	 */
	@Test
	public void getContacts_should_return_0()
	{
		when( registry.getContacts( any( ContactFilter.class ) ) ).thenReturn( new ArrayList() );
		ContactList result = controller.getContacts(request, response);
		
		assertEquals(0, result.getContacts().size());
	}
	
	/**
	 * Get entries - 2
	 */
	@Test
	public void getContacts_should_return_2()
	{
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(mock(Contact.class) );
		contacts.add(mock(Contact.class) );
		when( registry.getContacts( any( ContactFilter.class ) ) ).thenReturn( contacts );
		ContactList result = controller.getContacts(request, response);
		
		assertEquals(2, result.getContacts().size());
	}


	/**
	 * getFilter - Invalid filter!
	 */
	@Test
	public void getFilter_invalid()
	{
		request.addParameter("Sender", "Pale");

		ContactFilter[] filter = controller.getFilters(request);
		assertEquals(0, filter.length);
	}

	/**
	 * getFilter - order by age
	 */
	@Test
	public void getFilter_with_order()
	{
		request.addParameter("order", "asc");
		
		ContactFilter[] filter = controller.getFilters(request);
		
		assertEquals(1, filter.length);
	}

	/**
	 * getFilter - order by age - count 1
	 */
	@Test
	public void getFilter_with_order_count()
	{
		request.addParameter("order", "desc");
		request.addParameter("count", "1");

		ContactFilter[] filter = controller.getFilters(request);
		
		assertEquals(2, filter.length);
	}
}
