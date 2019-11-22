package com.enquery.service;

import com.enquery.model.Institute;
import com.enquery.repository.AddressRepository;
import com.enquery.repository.ContactRepository;
import com.enquery.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InstituteService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @Transactional
    public void saveInstitute(Institute institute){
        addressRepository.save(institute.getContact().getPermanentAddress());
        contactRepository.save(institute.getContact());
        instituteRepository.save(institute);
    }
}