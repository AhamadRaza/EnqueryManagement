package com.enquery.repository;

import com.enquery.model.Address;
import com.enquery.model.EnquiryCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryCourseRepository extends JpaRepository<EnquiryCourse, Long> {
}