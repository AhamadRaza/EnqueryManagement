package com.enquery.repository;

import com.enquery.model.Enquiry;
import org.springframework.stereotype.Service;

public class EnquiryCommand {
    Enquiry enquiry;
    Long[] courses;

    public Enquiry getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    public Long[] getCourses() {
        return courses;
    }

    public void setCourses(Long[] courses) {
        this.courses = courses;
    }
}