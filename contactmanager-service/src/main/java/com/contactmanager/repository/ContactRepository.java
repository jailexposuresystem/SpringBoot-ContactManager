package com.contactmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.contactmanager.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer>{
	
}
