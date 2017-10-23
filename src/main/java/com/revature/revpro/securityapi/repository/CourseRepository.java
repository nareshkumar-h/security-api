package com.revature.revpro.securityapi.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.revature.revpro.securityapi.model.Course;

@RepositoryRestResource(path="courses")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long>{
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	List<Course> findAll();
	
	
}
