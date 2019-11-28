package com.enquery.controller;

import com.enquery.dto.EnquiryCommand;
import com.enquery.model.*;
import com.enquery.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class EnqueryController {
    @Autowired private CourseService courseService;
    @Autowired private InstituteService instituteService;
    @Autowired private FollowUpService followUpService;
    @Autowired private EnquiryService enquiryService;
    @Autowired private EnquirySourceService enquirySourceService;

    @GetMapping("/")
    public String index(Model m) {
        m.addAttribute("cmd", new EnquiryCommand());
        return "/index"; //index.html page
    }

    @GetMapping("/save-enquiry")
    public String save(@ModelAttribute EnquiryCommand cmd) {
        enquiryService.saveEnquiry(cmd);
        return "redirect:/enq-list";
    }
    /*@GetMapping("/test-enquiry")
    public String test() {
        return enquiryRepository.findAllIds();
    }*/
    @GetMapping("/enq-list")
    public String enquiryList(@RequestParam(required = false) Long instId , Model model) {
        model.addAttribute("enquiryList" ,enquiryService.getEnquiryDTOList(instId));
        return "/enq-list";
    }

    @PostMapping("/save-followup")
    public String saveFollowUp(@RequestParam Long enquiryId , @RequestParam String followup , @RequestParam(required = false) String go){
        followUpService.saveFollowUp(enquiryId, followup);
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
        return courseService.findCourseListByInstituteId(instId);
    }

    @ModelAttribute("instituteList")
    public List<Institute> getInstList() {
        return instituteService.getCustomeList();
    }

    @ModelAttribute("sourceList")
    public List<EnquirySource> getSourceList() {
        return enquirySourceService.findListES();
    }

    @GetMapping("/enquiry-detail/{id}")
    public String enquiryDetail(@PathVariable Long id , Model model){
        model.addAttribute("dataMap" , enquiryService.getEnquiryDetailMap(id));
        model.addAttribute("followupList" , followUpService.getFollowupsByEnquiryId(id));
        return "/enquiry-detail";
    }

    /*@GetMapping("/testenquiry-detail/{id}")
    @ResponseBody
    public Map<String , Object> enquiryDetail(@PathVariable Long id ){

        return enquiryRepository.getEnquiryDetailMap(id);
    }*/
    @GetMapping(value = "/edit-enquiry/{id}")
    public String edit(@PathVariable Long id , Model model,EnquiryCommand ecmd){
        Enquiry cmd= ecmd.setEnquiry(enquiryService.findById(id));
               // enquiryService.findById(id);
        model.addAttribute("cmd", cmd);
        return "/index";
    }
    @GetMapping(value = "/delete-enquiry/{id}")
    public String delete(@PathVariable Enquiry id){
        enquiryService.delete(id);
        return "redirect:/enq-list"; // redirect:/enq-list.html
    }
}