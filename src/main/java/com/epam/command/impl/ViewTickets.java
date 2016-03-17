package com.epam.command.impl;

import com.epam.command.Command;
import com.epam.domain.Event;
import com.epam.domain.User;
import com.epam.services.BookingService;
import com.epam.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Component
public class ViewTickets implements Command {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Override
    public String getName() {
        return "view-tickets";
    }

    @Override
    public String getDescription() {
        return "Shows tickets";
    }

    @Override
    public User.UserRole getAllowedRole() {
        return User.UserRole.ADMIN;
    }

    @Override
    public void apply(BufferedReader reader, PrintStream out) {
        out.println("All events:");
        List<Event> events = eventService.getAll();
        for (Event event : events) {
            out.println(event.getName());
            out.println(event.getDates());
        }
        out.println("Please choose event");
        String eventName = null;
        try {
            eventName = reader.readLine();
        } catch (IOException e) {
            out.println("No such event");
        }
        Event chosenEvent = new Event();
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                chosenEvent = event;
            }
        }
        bookingService.getTicketsForEvent(chosenEvent, new Date());
    }
}
