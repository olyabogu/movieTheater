package com.epam.command;

import com.epam.domain.User;
import com.epam.exception.MovieException;
import com.epam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        try {
            outputStream.println("Enter user name: ");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();
            outputStream.println("Enter user role (ADMIN/CLIENT): ");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String role = reader.readLine();
            outputStream.println("Enter user email: ");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String email = reader.readLine();
            outputStream.println("Enter user birth date (dd-MM-yyyyyy):");

            String date = reader.readLine();
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyyyy");

            Date birthDate = formatter.parse(date);
            User user = new User(name, birthDate, User.UserRole.valueOf(role.toUpperCase()), email);
            userService.register(user);
            outputStream.println("User registered successfully ");

        } catch (IOException | MovieException e) {
            outputStream.println("Register new user produce an error " + e.getMessage());
        } catch (ParseException e) {
            outputStream.println("Invalid Date Format " + e.getMessage());
        }
    }
}
