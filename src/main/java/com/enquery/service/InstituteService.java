package com.enquery.service;

import com.enquery.dto.InstituteListDAO;
import com.enquery.model.*;
import com.enquery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public Institute findById(Long id){
        addressRepository.findById(id);
        contactRepository.findById(id);
        instituteRepository.findById(id);
        return instituteRepository.findById(id).get();
    }
    public void delete(Institute institute){
        addressRepository.delete(institute.getContact().getPermanentAddress());
        contactRepository.delete(institute.getContact());
        instituteRepository.delete(institute);
    }
}