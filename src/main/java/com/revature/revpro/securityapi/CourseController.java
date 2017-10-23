package com.revature.revpro.securityapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpro.securityapi.model.Course;
import com.revature.revpro.securityapi.repository.CourseRepository;

@RestController
//@RequestMapping("courses")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	//@GetMapping
	public List<Course> findAll() {
		System.out.println("Course->findAll");

		return courseRepository.findAll();

	}
}
