package com.enquery.service;

import com.enquery.model.*;
import com.enquery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InstituteService {

    @Autowired private AddressRepository addressRepository;
    @Autowired private ContactRepository contactRepository;
    @Autowired private InstituteRepository instituteRepository;

    public List<InstituteListDAO> getInstList(){
        return instituteRepository.getInstList();
    }
    public List<Institute> getCustomeList(){
        return instituteRepository.getInstituteCustomeList();
    }

    @Transactional
    public void saveInstitute(Institute institute){
        addressRepository.save(institute.getContact().getPermanentAddress());
        contactRepository.save(institute.getContact());
        instituteRepository.save(institute);
    }
}