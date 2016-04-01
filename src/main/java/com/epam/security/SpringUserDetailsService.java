package com.epam.security;

import com.epam.domain.User;
import com.epam.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class SpringUserDetailsService implements UserDetailsService {

    private UserService springUserRepository;

    public SpringUserDetailsService(UserService springUserRepository) {
        this.springUserRepository = springUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        if (username.contains("@")) {
            user = springUserRepository.getUserByEmail(username);
        } else {
            user = springUserRepository.getUserByName(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException("Username '" + username + "' not found");
        } else {
            return user;
        }
    }
}
