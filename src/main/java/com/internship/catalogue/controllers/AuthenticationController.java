package com.internship.catalogue.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internship.catalogue.entities.Role;
import com.internship.catalogue.entities.RoleName;
import com.internship.catalogue.entities.User;
import com.internship.catalogue.jwtAuthentication.message.request.LoginForm;
import com.internship.catalogue.jwtAuthentication.message.request.SignUpForm;
import com.internship.catalogue.jwtAuthentication.message.response.JwtResponse;
import com.internship.catalogue.jwtAuthentication.message.response.MessageResponse;
import com.internship.catalogue.jwtAuthentication.security.jwt.JwtProvider;
import com.internship.catalogue.jwtAuthentication.security.services.UserPrinciple;
import com.internship.catalogue.repositories.RoleRepository;
import com.internship.catalogue.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
    JwtProvider jwtProvider;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
	
	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
    }
	
	
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        System.out.println(signUpRequest.getCin());
        User user = new User(signUpRequest.getCin(),signUpRequest.getNom(), signUpRequest.getEmail(), signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        
        Set<String> strRoles = new HashSet<String>();
        strRoles.add(signUpRequest.getRole());
        Set<Role> roles = new HashSet<Role>();
        System.out.println(roles);

        strRoles.forEach(role -> {
        	
        	System.out.println(role);
        	
        	switch(role) {
    		case "admin":
    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
    			roles.add(adminRole);
    			
    			break;
    		case "responsable d'agence":
            	Role etudiantRole = roleRepository.findByName(RoleName.ROLE_RESPONSABLE_AGENCE)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
            	roles.add(etudiantRole);
            	
    			break;
    			
    		default:
        		Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
        		roles.add(userRole);        			
    	}
        	}
        );
        
        user.setRoles(roles);
        userRepository.save(user);

        System.out.println(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        
    }

}
