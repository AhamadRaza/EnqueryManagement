package com.enquery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface EnquiryDTO {
    Long getEnquiryId();
    String getPhone();
    String getName();
    String getCourses();
    Integer getFollowUpCount();

    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd/MM/yyyy")
    Date getDoe();
    Float getFees();
}