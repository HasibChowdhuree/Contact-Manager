package com.contactmanager.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.contactmanager.dao.userrepo;
import com.contactmanager.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private userrepo userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.getUserByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("could not find user");
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return customUserDetails;
	}

}