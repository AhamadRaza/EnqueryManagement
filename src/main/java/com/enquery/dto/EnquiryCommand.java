package com.enquery.dto;

import com.enquery.model.Enquiry;
import org.springframework.stereotype.Service;

public class EnquiryCommand {
    Enquiry enquiry;
    Long[] courses;

    public Enquiry getEnquiry() {
        return enquiry;
    }

    public Enquiry setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
        return enquiry;
    }

    public Long[] getCourses() {
        return courses;
    }

    public void setCourses(Long[] courses) {
        this.courses = courses;
    }
}