package com.enquery.repository;

import com.enquery.model.Address;
import com.enquery.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
}