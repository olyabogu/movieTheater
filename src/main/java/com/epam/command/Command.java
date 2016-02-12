package com.epam.command;

import com.epam.domain.User;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
public interface Command {

    String getName();

    String getDescription();

    User.UserRole getAllowedRole();

    void apply(BufferedReader reader, PrintStream out);
}
