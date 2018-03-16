package com.contactmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactmanager.model.Contact;
import com.contactmanager.repository.ContactsDAO;

@RestController
@RequestMapping("/contacts")
public class ContactManagerController {

	@Autowired
	private ContactsDAO contactsDAO;

	@GetMapping("/findallcontacts")
	public List<Contact> findAllContacts() {

		List<Contact> contactList = contactsDAO.findAll();
		return contactList;
	}

	@GetMapping("/findcontactbyid/{id}")
	public Contact findById(@PathVariable("id") final Integer id) {

		Contact contact = contactsDAO.findById(id);
		return contact;
	}
	
	@PostMapping("/addcontact")
	public Contact addContact(@RequestBody final Contact contact){
		
		Contact addedContact= contactsDAO.addContact(contact);
		return addedContact;
	}
	
	@PutMapping("/updatecontact/{id}")
	public Contact updateContact(@PathVariable("id")final Integer id,@RequestBody final Contact updateContact){
		
		Contact updatedContact = contactsDAO.updateContact(id, updateContact);
		return updatedContact;
	}

	@DeleteMapping("/deletecontact/{id}")
	public Contact deleteContact(@PathVariable("id")final Integer id){
		
		Contact deletedContact = contactsDAO.deleteContact(id);
		return deletedContact;
	}
}
