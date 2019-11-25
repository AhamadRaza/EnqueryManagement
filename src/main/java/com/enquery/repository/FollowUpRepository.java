package com.enquery.repository;

import com.enquery.model.Address;
import com.enquery.model.Followup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowUpRepository extends JpaRepository<Followup, Long> {

    @Query("SELECT new com.enquery.model.Followup(f.followupId, f.doe, f.detail) FROM Followup as f WHERE f.enquiry.enquiryId=?1 ORDER BY f.doe desc")
    List<Followup> getFollowupsByEnquiryId(Long enquiryId);
}