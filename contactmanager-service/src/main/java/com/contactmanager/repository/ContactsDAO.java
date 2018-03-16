package com.contactmanager.repository;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.contactmanager.model.Contact;

@Repository
public class ContactsDAO {

	@Autowired
	private ContactRepository contactRepository;

	public List<Contact> findAll() {

		List<Contact> contactList = contactRepository.findAll();
		return contactList;
	}

	public Contact findById(Integer id) {

		Contact contact = contactRepository.findOne(id);
		return contact;
	}

	public Contact addContact(Contact contact) {

		Contact newContact = contactRepository.save(contact);
		return newContact;
	}

	public Contact updateContact(Integer id, Contact updateContact) {

		Contact oldContact = contactRepository.findOne(id);
		BeanUtils.copyProperties(updateContact, oldContact);
		Contact updatedContact = contactRepository.save(oldContact);
		return updatedContact;
	}
	
	public Contact deleteContact(Integer id) {

		Contact deleteContact = contactRepository.findOne(id);
		contactRepository.delete(deleteContact);
		return deleteContact;
	}
}
