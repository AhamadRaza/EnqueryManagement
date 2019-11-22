package com.enquery.controller;

import com.enquery.model.Institute;
import com.enquery.repository.InstituteRepository;
import com.enquery.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

@Controller
public class InstituteController {
    @Autowired
    private InstituteService instituteService;

    @Autowired
    private InstituteRepository instituteRepository;

    @GetMapping(value = "/institute-form")
    public String form(Model model){
        model.addAttribute("cmd" , new Institute());
        return "/institute-form";
    }

    @GetMapping(value = "/save-institute")
    public String save(@ModelAttribute Institute institute){
        institute.getContact().setName(institute.getName());
        institute.setDoe(new Date());
        instituteService.saveInstitute(institute);
        return "redirect:/institute-list";
    }
    @GetMapping(value = "/institute-list")
    public String list(Model model){
        //model.addAttribute("instList" , instituteRepository.findAll());
        model.addAttribute("instList" , instituteRepository.getInstList());
        return "/institute-list";
    }
}