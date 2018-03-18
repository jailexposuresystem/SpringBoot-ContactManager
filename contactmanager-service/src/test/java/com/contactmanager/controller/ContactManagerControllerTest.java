package com.contactmanager.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.contactmanager.model.Contact;
import com.contactmanager.service.ContactsService;

public class ContactManagerControllerTest {

	@InjectMocks
	private ContactManagerController cmc;

	@Mock
	ContactsService contactsService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAllContacts() {
		Contact contact = new Contact();
		contact.setId(1);

		List<Contact> list = new ArrayList<Contact>();
		list.add(contact);

		when(contactsService.findAll()).thenReturn(list);

		assertEquals(1, cmc.findAllContacts().size());
		verify(contactsService).findAll();

	}

	@Test
	public void testFindById() {
		Contact contact = new Contact();
		contact.setId(1);

		when(contactsService.findById(1)).thenReturn(contact);

		assertEquals(1, cmc.findById(1).getId().intValue());
		verify(contactsService).findById(1);

	}

	@Test
	public void testAddContact() {
		Contact contact = new Contact();
		contact.setId(1);

		when(contactsService.addContact(contact)).thenReturn(contact);

		assertEquals(1, cmc.addContact(contact).getId().intValue());
		verify(contactsService).addContact(contact);
	}

	@Test
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

	}
}
