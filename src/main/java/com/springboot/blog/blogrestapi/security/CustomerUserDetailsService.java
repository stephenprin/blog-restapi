package com.springboot.blog.blogrestapi.security;

import com.springboot.blog.blogrestapi.entity.User;
import com.springboot.blog.blogrestapi.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameEmail) throws UsernameNotFoundException {
        User user= userRepository.findByUsernameOrEmail(usernameEmail,  usernameEmail)
                .orElseThrow(()->new UsernameNotFoundException("User not found with the username or email" + usernameEmail));
        Set<GrantedAuthority>  authorities=user.getRoles().stream()
                .map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}

