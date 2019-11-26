package com.enquery.service;

import com.enquery.model.Course;
import com.enquery.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseService {
    @Autowired private CourseRepository courseRepository;

    public List<Course> findCourseListByInstituteId(Long instituteId){
        return courseRepository.findCourseListByInstitute_instituteId(instituteId);
    }

    public List<Map<String , Object>> getCourse(){
        return courseRepository.getCourse();
    }

    public void save(Course course){
        courseRepository.save(course);
    }

    public Course findById(Long id){
        return courseRepository.findById(id).get();
    }

    public void delete(Long id){
        courseRepository.deleteById(id);
    }
}