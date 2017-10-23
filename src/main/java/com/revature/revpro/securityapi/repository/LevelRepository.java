package com.revature.revpro.securityapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revature.revpro.securityapi.model.Level;

@RepositoryRestResource(path="levels")
public interface LevelRepository extends PagingAndSortingRepository<Level, Long>{

	
	
}
