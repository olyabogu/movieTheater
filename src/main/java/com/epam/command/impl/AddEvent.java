package com.epam.command.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.command.Command;
import com.epam.exception.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.domain.Event;
import com.epam.domain.User;
import com.epam.services.EventService;

/**
 * @author Olga_Bogutska.
 */
@Component
public class AddEvent implements Command {

	@Autowired
	private EventService eventService;

	@Override
	public String getName() {
		return "enter-event";
	}

	@Override
	public String getDescription() {
		return "Add new events";
	}

	@Override
	public User.UserRole getAllowedRole() {
		return User.UserRole.ADMIN;
	}

	@Override
	public void apply(BufferedReader reader, PrintStream out) {
		out.println("Enter new event name :");
		String name, eventDate, price, rating;

		try {
			name = reader.readLine();

			out.println("Enter new event date (dd-MM-yyyy):");

			eventDate = reader.readLine();
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			List<Date> dates = new ArrayList<>();

			Date date = formatter.parse(eventDate);
			dates.add(date);

			out.println("Enter new event price :");

			price = reader.readLine();

			out.println("Enter event rating : HIGH, MID, LOW");
			rating = reader.readLine();

			Event event = new Event(dates, Double.valueOf(price), name, Event.Rating.valueOf(rating));

			eventService.createEvent(event);
			out.println("Event " + name + " added successfully!");

		} catch (IOException | ParseException | MovieException e) {
			out.println("Adding new event failed! Caused by " + e.getMessage());
		}
	}
}
