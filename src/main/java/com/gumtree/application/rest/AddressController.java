package com.gumtree.application.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gumtree.application.Ensure;
import com.gumtree.application.model.AddressResourceRegistry;
import com.gumtree.application.model.Contact;
import com.gumtree.application.model.ContactFilter;
import com.gumtree.application.model.ContactFilterOptions;
import com.gumtree.application.model.ContactList;
import com.gumtree.application.model.Gender;
import com.gumtree.application.model.Order;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "gumtree/addressbook")
public class AddressController {

	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	private final AddressResourceRegistry registry;

	/**
	 * Create a the controller with the supplied registry
	 * 
	 * @param registry
	 */
	@Autowired
	public AddressController(AddressResourceRegistry registry) {
		Ensure.notNull(registry, "Registry cannot be null");
		this.registry = registry;
	}

	/**
	 * Returns a list of all the contacts in the address book.
	 * Results can be filtered using valid request params
	 * Supported: count = # number of records to return
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public ContactList getContacts(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Get contacts");
	
		ContactList contacts = new ContactList( registry.getContacts( getFilters(request) ) );
		
		response.setStatus( HttpServletResponse.SC_OK );
		
		return contacts;
	}
	
	/**
	 * Returns a list of all the contacts in the address book.
	 * Results can be filtered using valid request params
	 * Supported: gender = "male/female"
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public Integer getContactCount(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Get contact count");
		
		List<Contact> contacts = registry.getContacts( getFilters(request) );
		Ensure.notNull(contacts, "must return a valid contact list");
		
		response.setStatus( HttpServletResponse.SC_OK );
		
		return contacts.size();
	}

	
	ContactFilter[] getFilters( HttpServletRequest request )
	{
		List<ContactFilter> filters = new ArrayList<ContactFilter>();
		Map<String, String[]> params = request.getParameterMap();
		
		for (Entry entry : params.entrySet()) {
			try {
				ContactFilterOptions option = ContactFilterOptions
						.valueOf(entry.getKey().toString().toUpperCase());
				
				String[] values = (String[]) entry.getValue();
				String value = String.valueOf( values[0] ).toUpperCase() ;
				
				switch (option) {
				case COUNT:
					// Ensure value is a valid int!
					Integer count = Integer.valueOf(value);
					if (count > 0) {
						filters.add(new ContactFilter(
								ContactFilterOptions.COUNT, count.toString()));
					}
					break;
				case GENDER:
					Gender gender = Gender.valueOf(value);
					filters.add(new ContactFilter(ContactFilterOptions.GENDER,
							gender.toString()));
					break;
				case ORDER:
					Order order = Order.valueOf(value);
					filters.add(new ContactFilter(ContactFilterOptions.ORDER,
							order.toString()));
					break;
				}
			} catch (Exception e) {
				logger.debug("Unable to create filter", e);
			}
		}
		
		
		return filters.toArray( new ContactFilter[filters.size()] );
	}
	
	
	
	
	
	
}
