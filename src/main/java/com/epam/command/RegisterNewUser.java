package com.epam.command;

import com.epam.Command;
import com.epam.domain.User;
import com.epam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by Olga Bogutska on 09.02.2016.
 */
@Component
public class RegisterNewUser implements Command {
    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return "register-user";
    }

    @Override
    public String getDescription() {
        return "Register new user";
    }

    @Override
    public User.UserRole getAllowedRole() {
        return User.UserRole.ANONYM;
    }

    @Override
    public void apply(BufferedReader reader, PrintStream outputStream) {
        outputStream.println("Do you want to register new user Y/N? ");
        String register;
        try {
            register = reader.readLine();

            switch (register) {
                case "Y":
                    outputStream.println("Enter user name: ");
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    String name = reader.readLine();
                    outputStream.println("Enter user role (ADMIN/CLIENT): ");
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    String role = reader.readLine();
                    outputStream.println("Enter user email: ");
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    String email = reader.readLine();
                    User user = new User(name, User.UserRole.valueOf(role), email);
                    userService.register(user);
                    outputStream.println("User registered successfully ");
                    break;
                case "N":
                    break;
            }
        } catch (IOException e) {
            outputStream.println("Register user produce an error");
        }
    }
}
