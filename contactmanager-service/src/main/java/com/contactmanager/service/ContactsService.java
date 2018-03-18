package com.contactmanager.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactmanager.model.Contact;
import com.contactmanager.repository.ContactRepository;

@Service
public class ContactsService {

	@Autowired
	private ContactRepository contactRepository;

	public List<Contact> findAll() {

		return (List<Contact>) contactRepository.findAll();
	}

	public Contact findById(Integer id) {

		return contactRepository.findOne(id);
	}

	public Contact addContact(Contact contact) {

		return contactRepository.save(contact);

	}

	public Contact updateContact(Integer id, Contact updateContact) {

		Contact oldContact = contactRepository.findOne(id);
		BeanUtils.copyProperties(updateContact, oldContact);// cpoing properties
															// from
															// updateContact to
															// oldContact
		Contact updatedContact = contactRepository.save(oldContact);
		return updatedContact;
	}

	public Contact deleteContact(Integer id) {

		Contact deleteContact = contactRepository.findOne(id);
		contactRepository.delete(deleteContact);
		return deleteContact;
	}
}
