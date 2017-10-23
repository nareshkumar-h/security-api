package com.revature.revpro.securityapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revature.revpro.securityapi.model.Category;

@RepositoryRestResource(path="categories")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>{

	
	
}
