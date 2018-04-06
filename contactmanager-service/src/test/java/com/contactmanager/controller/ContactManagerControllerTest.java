package com.contactmanager.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.contactmanager.model.Contact;
import com.contactmanager.service.ContactsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactManagerController.class)
public class ContactManagerControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ContactsService contactsService;

	@Test
	public void testFindAllContacts() throws Exception {
		Contact contact = new Contact();
		contact.setId(1);
		contact.setContact_number("2038933478");
		contact.setLocation("Texas");
		contact.setName("sanjay");

		List<Contact> list = new ArrayList<Contact>();
		list.add(contact);

		given(this.contactsService.findAll()).willReturn(list);
		this.mvc.perform(get("/contacts/findallcontacts").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].location", is("Texas")))
				.andExpect(jsonPath("$[0].name", is("sanjay")))
				.andExpect(jsonPath("$[0].contact_number", is("2038933478")));

	}

	@Test
	public void testFindById() throws Exception {
		Contact contact = new Contact();
		contact.setId(1);
		contact.setContact_number("2038933478");
		contact.setLocation("Texas");
		contact.setName("sanjay");

		given(this.contactsService.findById(1)).willReturn(contact);
		this.mvc.perform(get("/contacts/findcontactbyid/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.location", is("Texas")))
				.andExpect(jsonPath("$.name", is("sanjay")))
				.andExpect(jsonPath("$.contact_number", is("2038933478")));

	}

	@Test
	public void testAddContact() throws Exception {
		Contact contact = new Contact();
		contact.setId(1);
		contact.setContact_number("2038933478");
		contact.setLocation("Texas");
		contact.setName("sanjay");

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(contact);
		
		String abc = "{\"id\": 1, \"name\": \"Sanjay\", \"contact_number\": \"2038933478\", \"location\": \"Texas\"}";
		
		given(this.contactsService.addContact(contact)).willReturn(contact);
		this.mvc.perform(post("/contacts/addcontact").accept(MediaType.APPLICATION_JSON_VALUE)
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(jsonInString))	
				 				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.location", is("Texas")))
				.andExpect(jsonPath("$.name", is("sanjay")))
				.andExpect(jsonPath("$.contact_number", is("2038933478")));
	}

	/*@Test
	public void testUpdateContact() {
		Contact contact = new Contact();
		contact.setId(1);

		when(contactsService.updateContact(1, contact)).thenReturn(contact);

		assertEquals(1, cmc.updateContact(1, contact).getId().intValue());
		verify(contactsService).updateContact(1, contact);

	}

	@Test
	public void testDeleteContact() {
		final Contact contact = new Contact();
		contact.setId(1);

		when(contactsService.deleteContact(1)).thenReturn(contact);

		assertEquals(1, cmc.deleteContact(1).getId().intValue());
		verify(contactsService).deleteContact(1);

	}*/
}
