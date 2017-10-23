package com.revature.revpro.securityapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.revature.revpro.securityapi.model.Course;

@RepositoryRestResource(path="projects")
public interface ProjectRepository extends PagingAndSortingRepository<Course, Long>{

	
	
}
