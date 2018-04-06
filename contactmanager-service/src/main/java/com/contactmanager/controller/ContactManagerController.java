package com.contactmanager.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.contactmanager.model.Contact;
import com.contactmanager.service.ContactsService;
import com.contactmanager.util.CustomErrorType;
import com.google.common.base.Preconditions;

@RestController
@RequestMapping("/contacts")
public class ContactManagerController {

	private static final Logger LOG = LoggerFactory.getLogger(ContactManagerController.class);

	@Autowired
	private ContactsService contactsService;

	@GetMapping("/findallcontacts")
	public ResponseEntity<?> findAllContacts() {

		//return contactsService.findAll();
		LOG.info("Fetching all contacts");
		List<Contact> list = contactsService.findAll();
		if (list.isEmpty()) {
			LOG.error("No contacts found...!!");
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Contacts not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Contact>>(list, HttpStatus.OK);
	}

	@GetMapping("/findcontactbyid/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") final Integer id) {

		// Preconditions.checkNotNull(id, "Id parameter should not be null");

		// return contactsService.findById(id);

		LOG.info("Fetching Contact with id {}", id);
		Contact contact = contactsService.findById(id);
		if (contact == null) {
			LOG.error("Contact with id {} not found.", id);
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Contact with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);

	}

	@PostMapping("/addcontact")
	@ResponseBody
	public Contact addContact(@RequestBody final Contact contact) {

		LOG.info("Request to add contact", contact.getName().toString());

		// LOG.info("contact added");

		return contactsService.addContact(contact);

	}

	@PutMapping("/updatecontact/{id}")
	public Contact updateContact(@PathVariable("id") final Integer id, @RequestBody final Contact updateContact) {

		return contactsService.updateContact(id, updateContact);
	}

	@DeleteMapping("/deletecontact/{id}")
	public Contact deleteContact(@PathVariable("id") final Integer id) {

		return contactsService.deleteContact(id);
	}
}
