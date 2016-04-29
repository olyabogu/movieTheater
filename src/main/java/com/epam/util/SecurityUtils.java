package com.epam.util;

import com.epam.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

public class SecurityUtils {

    private static final String DEFAULT_ROLE = "REGISTERED_USER";

    public static Set<? extends GrantedAuthority> toAuthorities(Set<String> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if(roles != null && roles.size() != 0) {
            for(String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        } else {
            authorities.add(new SimpleGrantedAuthority(DEFAULT_ROLE));
        }
        return authorities;
    }

    public static boolean isUserAuthenticated(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated();
    }

    public static User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }
}
