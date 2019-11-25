package com.enquery.controller;

import com.enquery.model.Course;
import com.enquery.model.EnquirySource;
import com.enquery.model.Institute;
import com.enquery.repository.*;
import com.enquery.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
public class EnqueryController {

    @Autowired private InstituteRepository instituteRepository;
    @Autowired private EnquirySourceRepository enquirySourceRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private EnquiryRepository enquiryRepository;
    @Autowired private InstituteService instituteService;
    @Autowired private FollowUpRepository followUpRepository;

    @GetMapping("/")
    public String index(Model m) {
        m.addAttribute("cmd", new EnquiryCommand());
        return "/index"; //index.html page
    }

    @GetMapping("/save-enquiry")
    @ResponseBody
    public String save(@ModelAttribute EnquiryCommand cmd) {
       instituteService.saveEnquiry(cmd);
        return "redirect:/enq-list";
    }
    /*@GetMapping("/test-enquiry")
    public String test() {
        return enquiryRepository.findAllIds();
    }*/
    @GetMapping("/enq-list")
    public String enquiryList(@RequestParam(required = false) Long instId , Model model) {
        model.addAttribute("enquiryList" ,enquiryRepository.getEnquiryDTOList(instId));
        return "/enq-list";
    }

    @PostMapping("/save-followup")
    public String saveFollowUp(@RequestParam Long enquiryId , @RequestParam String followup , @RequestParam(required = false) String go){
        instituteService.saveFollowUp(enquiryId, followup);
        if(go!=null && go.equals("eview")){
            return "redirect:/enquiry-detail/"+enquiryId;
        }
        else {
            return "redirect:/enq-list";
        }
    }

    @GetMapping("/get-courses")
    @ResponseBody
    public List<Course> getCourseByInstitudeId(@RequestParam Long instId){
        return courseRepository.findCourseListByInstitute_instituteId(instId);
    }

    @ModelAttribute("instituteList")
    public List<Institute> getInstList() {
        return instituteRepository.getInstituteList();
    }

    @ModelAttribute("sourceList")
    public List<EnquirySource> getSourceList() {
        return enquirySourceRepository.findAll();
    }

    @GetMapping("/enquiry-detail/{id}")
    public String enquiryDetail(@PathVariable Long id , Model model){
        model.addAttribute("dataMap" , enquiryRepository.getEnquiryDetailMap(id));
        model.addAttribute("followupList" , followUpRepository.getFollowupsByEnquiryId(id));
        return "/enquiry-detail";
    }

    /*@GetMapping("/testenquiry-detail/{id}")
    @ResponseBody
    public Map<String , Object> enquiryDetail(@PathVariable Long id ){

        return enquiryRepository.getEnquiryDetailMap(id);
    }*/
}