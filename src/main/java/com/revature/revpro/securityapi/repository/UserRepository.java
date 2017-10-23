package com.revature.revpro.securityapi.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;

import com.revature.revpro.securityapi.model.User;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	//@RestResource(path = "login")
	
	@RestResource(path = "login")
	User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	//@RestResource(path = "findByName" ,  exported= true)
	//Optional<User> findByName(@Param("email") String name);
	
	
	
	@RestResource(path = "findByName")
	Optional<User> findByName(@Param("name") String name);
	
	@RestResource(path = "findByEmail" )
	Optional<User> findByEmail(@Param("email") String email);
	
}
