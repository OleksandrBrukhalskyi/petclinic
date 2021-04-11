package com.bov.petclinic.security.service;

import com.bov.petclinic.entity.User;
import com.bov.petclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userService.findByLogin(username));
        User user = userOptional.orElseThrow(() -> new RuntimeException("User is not found"));
        return new org.springframework.security.
                core.userdetails.
                User(user.getLogin(),user.getPassword(),
                user.isEnabled(),true,true,
                true,getAuthorities(user.getRole().getName()));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
//    @Override
//    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = userService.findByLogin(login);
//        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
//    }
}
