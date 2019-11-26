package com.enquery.repository;

import com.enquery.model.Address;
import com.enquery.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstituteRepository extends JpaRepository<Institute, Long> {

    @Query("SELECT i.instituteId AS instituteId, i.name AS name, i.contact.phone AS phone, i.contact.email AS email, i.contact.permanentAddress.city AS city FROM  Institute AS i")
    public List<InstituteListDAO> getInstList();

    @Query("SELECT new com.enquery.model.Institute(i.instituteId , i.name) FROM  Institute AS i")
    public List<Institute> getInstituteCustomeList();
}