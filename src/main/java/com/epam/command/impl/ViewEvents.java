package com.epam.command.impl;

import com.epam.command.Command;
import com.epam.domain.Event;
import com.epam.domain.UserRole;
import com.epam.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by Olga Bogutska on 09.02.2016.
 */
@Component
public class ViewEvents implements Command {
    @Autowired
    private EventService eventService;

    @Override
    public String getName() {
        return "viewEvents";
    }

    @Override
    public String getDescription() {
        return "View events with air dates and time";
    }

    @Override
    public UserRole getAllowedRole() {
        return UserRole.CLIENT;
    }

    @Override
    public void apply(BufferedReader reader, PrintStream outputStream) {
        List<Event> events = eventService.getAll();
        for(Event event : events) {
            outputStream.println(event.toString());
        }
    }
}
