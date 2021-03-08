package com.internship.catalogue.jwtAuthentication.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internship.catalogue.entities.User;
import com.internship.catalogue.repositories.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	
        User user = null;
		try {
			user = (User) userRepository.findByUsername(username)
			        	.orElseThrow(() -> 
			                new UsernameNotFoundException("User Not Found with -> username or email : " + username)
			);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return UserPrinciple.build(user);
    }
}

