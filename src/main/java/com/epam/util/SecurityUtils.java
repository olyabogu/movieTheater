package com.epam.util;

import com.epam.domain.User;
import com.epam.domain.UserRole;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SecurityUtils {

    private static final String DEFAULT_ROLE = UserRole.REGISTERED_USER.name();

    private SecurityUtils() {
    }

    public static Set<? extends GrantedAuthority> toAuthorities(List<String> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if(roles != null && roles.isEmpty()) {
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
