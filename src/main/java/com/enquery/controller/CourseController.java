package com.enquery.controller;

import com.enquery.model.Course;
import com.enquery.model.Institute;
import com.enquery.repository.CourseRepository;
import com.enquery.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @GetMapping("/courses")
    public String Course(Model model){
        model.addAttribute("cmd" , new Course());
        return "/courses";
    }
    @GetMapping("/save-course")
    public String save(@ModelAttribute Course course){
        courseRepository.save(course);
        return "redirect:/courses";
    }

    @ModelAttribute("instituteList")
    public List<Institute> getInstituteList(){
        //return instituteRepository.findAll();
        return instituteRepository.getInstituteList();
    }
    @ModelAttribute("courseList")
    public List<Map<String,Object>> getCourseList(){
        //return courseRepository.findAll();
        return courseRepository.getCourse();
    }
    /*@GetMapping("/course-temp")
    @ResponseBody
    public List<Map<String,Object>> getCoursetemp(){
        //return courseRepository.findAll();
        return courseRepository.getCourse();
    }*/
}