package com.enquery.service;

import com.enquery.model.*;
import com.enquery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.function.LongConsumer;

@Service
public class InstituteService {

    @Autowired private AddressRepository addressRepository;
    @Autowired private ContactRepository contactRepository;
    @Autowired private InstituteRepository instituteRepository;
    @Autowired private EnquirySourceRepository enquirySourceRepository;
    @Autowired private EnquiryRepository enquiryRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private EnquiryCourseRepository enquiryCourseRepository;
    @Autowired private  FollowUpRepository followUpRepository;

    @Transactional
    public void saveInstitute(Institute institute){
        addressRepository.save(institute.getContact().getPermanentAddress());
        contactRepository.save(institute.getContact());
        instituteRepository.save(institute);
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

    @Transactional
    public void saveFollowUp(Long enquiryId , String detail){
        Followup fo = new Followup();
        fo.setDetail(detail);
        fo.setEnquiry(enquiryRepository.getOne(enquiryId));
        fo.setDoe(new Date());
        followUpRepository.save(fo);
    }
}