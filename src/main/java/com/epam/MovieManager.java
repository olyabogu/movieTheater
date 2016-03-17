package com.epam;

import com.epam.command.Command;
import com.epam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

@Component
public class MovieManager {

    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private List<Command> commands;
    private Map<String, Command> commandMap;

    @PostConstruct
    private void init() {
        Map<String, Command> map = new HashMap<>();
        for (Command command : commands) {
            map.put(command.getName(), command);
        }
        commandMap = unmodifiableMap(map);
    }

    public void startManager(InputStream inputStream, PrintStream outputStream) throws IOException {
        outputStream.println("Print 'help' to show all commands.");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String commandName;
        while (!(commandName = bufferedReader.readLine()).equalsIgnoreCase("q")) {
            if ("help".equals(commandName.trim())) {
                printHelp();
                continue;
            }
            Command command = commandMap.get(commandName);

            if (commandName.equals("register-user")) {
                command.apply(bufferedReader, outputStream);
            }
            if (!commandName.equals("login") && !securityContext.isLoggedIn()) {
                outputStream.println("Please login first.");
                continue;
            }
            if (command != null) {
                if (securityContext.isAdmin()) {
                    command.apply(bufferedReader, outputStream);
                } else {
	                if (commandName.equals("login") && User.UserRole.ANONYM.equals(command.getAllowedRole()) ) {
		                command.apply(bufferedReader, outputStream);
		                continue;
	                }
                    if (User.UserRole.CLIENT.equals(command.getAllowedRole())) {
                        command.apply(bufferedReader, outputStream);
                    } else {
                        outputStream.println("Not allowed to clients!");
                    }
                }
            } else {
                outputStream.println("No such command. Print 'help' to show all commands.");
            }
        }
        outputStream.println("Exiting");
    }

    private void printHelp() {
        for (Command command : commands) {
            System.out.println(command.getName() + " - " + command.getDescription());
        }
    }
}
