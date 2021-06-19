package com.apoorv.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apoorv.api.model.Registration;
import com.apoorv.api.repository.RegistrationRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private RegistrationRepository repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("checking email from service");
		Registration user = repo.findByEmail(username);
		System.out.println("user : "+user);
		if (user == null) return null;
		
		String gotUsername = user.getEmail();
		String gotPassword = user.getPassword();
		System.out.println("return object");
		System.out.println(gotUsername);
		System.out.println(gotPassword);
		return new User(gotUsername, gotPassword, new ArrayList<>());
	}
}


