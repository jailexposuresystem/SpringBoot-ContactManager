package com.contactmanager.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
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
import com.contactmanager.repository.ContactRepository;
import com.contactmanager.service.ContactsService;

public class ContactManagerServiceTest {
	
	@InjectMocks
	private ContactsService cs;
	
	@Mock
	ContactRepository contactRepository;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAll(){
		Contact contact = new Contact();
		contact.setId(1);

		List<Contact> list = new ArrayList<Contact>();
		list.add(contact);

		when(contactRepository.findAll()).thenReturn(list);

		assertEquals(1, cs.findAll().size());
		verify(contactRepository).findAll();
	}
	
	@Test
	public void testFindById() {
		Contact contact = new Contact();
		contact.setId(1);

		when(contactRepository.findOne(1)).thenReturn(contact);

		assertEquals(1, cs.findById(1).getId().intValue());
		verify(contactRepository).findOne(1);

	}

	@Test
	public void testAddContact() {
		Contact contact = new Contact();
		contact.setId(1);

		when(contactRepository.save(contact)).thenReturn(contact);

		assertEquals(1, cs.addContact(contact).getId().intValue());
		verify(contactRepository).save(contact);
	}

@Test
	public void testUpdateContact() {
		Contact contact = new Contact();
		contact.setId(1);   contact.setName("sanjay");

		when(contactRepository.save(contact)).thenReturn(contact);

		assertEquals("sanjay", cs.updateContact(1, contact).getName().toString());
		verify(contactRepository).save(contact);

	}

	@Test
	public void testDeleteContact() {
		Contact contact = new Contact();
		contact.setId(1);

		doNothing().when(contactRepository).delete(contact); 

		//assertNull(cs.deleteContact(1).getId().intValue());
		assertEquals(1, cs.deleteContact(1).getId().intValue());
		verify(contactRepository).delete(1);

	}
}
