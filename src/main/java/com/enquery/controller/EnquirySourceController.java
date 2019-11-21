package com.enquery.controller;

import com.enquery.model.EnquirySource;
import com.enquery.repository.EnquirySourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EnquirySourceController {
    @Autowired
    private EnquirySourceRepository enquirySourceRepository;

    @GetMapping(value = "/sources")
    public String enquirySource(Model model){
        EnquirySource cmd=new EnquirySource();
        model.addAttribute("cmd" , cmd);
        return "/sources";
    }

    @GetMapping(value = "/save-enquiry-source")
    public String save(@ModelAttribute EnquirySource es){
        enquirySourceRepository.save(es);
        return "redirect:/sources"; // redirect:/sources.html
    }

    @GetMapping(value = "/edit-enquiry-source/{id}")
    public String edit(@PathVariable Long id , Model model){
        EnquirySource cmd= enquirySourceRepository.findById(id).get();
        model.addAttribute("cmd", cmd);
        return "/sources";
    }

    @GetMapping(value = "/delete-enquiry-source/{id}")
    public String delete(@PathVariable Long id){
        enquirySourceRepository.deleteById(id);
        return "redirect:/sources"; // redirect:/sources.html
    }

    @ModelAttribute("enquirySourceList")
    public List<EnquirySource> getEnquirySourceList(){
        return enquirySourceRepository.findAll();
    }
}