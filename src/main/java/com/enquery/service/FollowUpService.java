package com.enquery.service;

import com.enquery.model.Followup;
import com.enquery.repository.EnquiryRepository;
import com.enquery.repository.FollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class FollowUpService {
    @Autowired private EnquiryRepository enquiryRepository;
    @Autowired private FollowUpRepository followUpRepository;

    @Transactional
    public void saveFollowUp(Long enquiryId , String detail){
        Followup fo = new Followup();
        fo.setDetail(detail);
        fo.setEnquiry(enquiryRepository.getOne(enquiryId));
        fo.setDoe(new Date());
        followUpRepository.save(fo);
    }

    public List<Followup> getFollowupsByEnquiryId(Long enquiryId){
        return followUpRepository.getFollowupsByEnquiryId(enquiryId);
    }
}