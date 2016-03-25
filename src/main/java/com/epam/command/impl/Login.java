package com.epam.command.impl;

import com.epam.SecurityContext;
import com.epam.command.Command;
import com.epam.domain.User;
import com.epam.domain.UserRole;
import com.epam.exception.MovieException;
import com.epam.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Component
public class Login implements Command {
    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "Login a user.";
    }

    @Override
    public UserRole getAllowedRole() {
        return UserRole.ANONYM;
    }

    @Override
    public void apply(BufferedReader reader, PrintStream out) {
        out.println("Write user name");
        try {
            String username = reader.readLine();

            User user = userService.getUserByName(username);
            if (user != null) {
                securityContext.login(user);
                out.println("User successfully logged in!");
            } else {
                out.println("No such user!");
            }
        } catch (IOException | MovieException e) {
	        out.println("Login failed due to "+ e.getMessage());
        }
    }
}
