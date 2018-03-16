package com.contactmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactmanager.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
	
}
