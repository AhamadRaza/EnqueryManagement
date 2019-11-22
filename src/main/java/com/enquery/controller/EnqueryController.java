package com.enquery.controller;

import com.enquery.model.EnquirySource;
import com.enquery.model.Institute;
import com.enquery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class EnqueryController {

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private EnquirySourceRepository enquirySourceRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private InstituteListDAO instituteListDAO;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("cmd", new EnquiryCommand());
        return "/index";
    }

    @ModelAttribute("instituteList")
    public List<Institute> getInstList(){
        return instituteRepository.getInstituteList();
    }
    @ModelAttribute("sourceList")
    public List<EnquirySource> getSourceList(){
        return enquirySourceRepository.findAll();
    }

}