package com.enquery.service;

import com.enquery.model.EnquirySource;
import com.enquery.repository.EnquirySourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquirySourceService {
    @Autowired private EnquirySourceRepository enquirySourceRepository;

    public void saveEnquirySource(EnquirySource es){
        enquirySourceRepository.save(es);
    }

    public EnquirySource findById(Long id){
        return enquirySourceRepository.findById(id).get();
    }

    public void delete(Long id){
         enquirySourceRepository.deleteById(id);
    }
    public List<EnquirySource> findListES(){
        return enquirySourceRepository.findAll();
    }
}