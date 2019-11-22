package com.enquery.repository;

import com.enquery.model.Address;
import com.enquery.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}