package com.enquery.repository;

import com.enquery.model.Address;
import com.enquery.model.Followup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowUpRepository extends JpaRepository<Followup, Long> {
}