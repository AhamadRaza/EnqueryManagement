package com.enquery.controller;

import com.enquery.model.Course;
import com.enquery.model.Institute;
import com.enquery.service.CourseService;
import com.enquery.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
public class CourseController {
    @Autowired private CourseService courseService;
    @Autowired private InstituteService instituteService;

    @GetMapping("/courses")
    public String Course(Model model){
        model.addAttribute("cmd" , new Course());
        return "/courses";
    }
    @GetMapping("/save-course")
    public String save(@ModelAttribute Course course){
        courseService.save(course);
        return "redirect:/courses";
    }

    @ModelAttribute("instituteList")
    public List<Institute> getInstituteList(){
        //return instituteService.findAll();
        return instituteService.getCustomeList();
    }
    @ModelAttribute("courseList")
    public List<Map<String,Object>> getCourseList(){
        //return courseService.findAll();
        return courseService.getCourse();
    }
    /*@GetMapping("/course-temp")
    @ResponseBody
    public List<Map<String,Object>> getCoursetemp(){
        //return courseRepository.findAll();
        return courseService.getCourse();
    }*/
}