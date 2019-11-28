package com.enquery.service;

import com.enquery.dto.EnquiryCommand;
import com.enquery.dto.EnquiryDTO;
import com.enquery.model.Address;
import com.enquery.model.Contact;
import com.enquery.model.Enquiry;
import com.enquery.model.EnquiryCourse;
import com.enquery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EnquiryService {
    @Autowired private AddressRepository addressRepository;
    @Autowired private ContactRepository contactRepository;
    @Autowired private InstituteRepository instituteRepository;
    @Autowired private EnquirySourceRepository enquirySourceRepository;
    @Autowired private EnquiryRepository enquiryRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private EnquiryCourseRepository enquiryCourseRepository;

    public String findAllId(){
        return enquiryRepository.findAllIds();
    }

    public List<EnquiryDTO> getEnquiryDTOList(Long instId){
        return enquiryRepository.getEnquiryDTOList(instId);
    }

    public Map<String, Object> getEnquiryDetailMap(Long enquiryId){
        return enquiryRepository.getEnquiryDetailMap(enquiryId);
    }

    public Enquiry findById(Long id){

        return enquiryRepository.findById(id).get();
    }

    public void delete(Enquiry enquiry){
        addressRepository.delete(enquiry.getContact().getPermanentAddress());
        contactRepository.delete(enquiry.getContact());
        enquiryRepository.delete(enquiry);
    }

    @Transactional
    public void saveEnquiry(EnquiryCommand cmd){
        Address a = cmd.getEnquiry().getContact().getPermanentAddress();
        addressRepository.save(a);

        Contact c = cmd.getEnquiry().getContact();
        contactRepository.save(c);

        Enquiry e = cmd.getEnquiry();
        e.setInstitute(instituteRepository.getOne(e.getInstitute().getInstituteId()));
        e.setEnquirySource(enquirySourceRepository.getOne(e.getEnquirySource().getEnquirySourceId()));
        Date d = new Date();
        e.setDoe(d);
        e.setLastUpdate(d);
        enquiryRepository.save(e);

        Long[] courseIds = cmd.getCourses();
        for(Long courseId : courseIds) {
            EnquiryCourse es = new EnquiryCourse();
            es.setEnquiry(e);
            es.setCourse(courseRepository.getOne(courseId));
            enquiryCourseRepository.save(es);
        }
    }
}