package com.epam.command;

import com.epam.Command;
import com.epam.SecurityContext;
import com.epam.domain.User;
import com.epam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.*;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Component
public class Login implements Command {
    @Autowired
    private SecurityContext securityContext;
    @Inject
    private UserService userService;

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "Login a user.";
    }

    @Override
    public User.UserRole getAllowedRole() {
        return User.UserRole.ANONYM;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
