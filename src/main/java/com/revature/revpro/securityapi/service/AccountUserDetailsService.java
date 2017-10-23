package com.revature.revpro.securityapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.revpro.securityapi.model.User;
import com.revature.revpro.securityapi.repository.UserRepository;

@Service
public class AccountUserDetailsService implements UserDetailsService {

	
    private UserRepository accountRepository;

    @Autowired
    public AccountUserDetailsService(UserRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
    	System.out.println("LoadByUsername:" + email );
        
    	Optional<User> findByEmail = accountRepository
                .findByEmail(email);
        
    	CustomUser user = findByEmail
                .map(account -> new CustomUser(account.getId(), account.getEmail(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER")))
                .orElseThrow(() -> new UsernameNotFoundException("Could not find " + email));
        
        System.out.println("LoadByEmail :" + user);
		return user;
    }
}