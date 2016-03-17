package com.epam.command.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.SecurityContext;
import com.epam.command.Command;
import com.epam.domain.Event;
import com.epam.domain.User;
import com.epam.exception.MovieException;
import com.epam.services.BookingService;
import com.epam.services.EventService;

/**
 * @author Olga_Bogutska.
 */
@Component
public class GetTicketPrice implements Command {

	@Autowired
	private EventService eventService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private SecurityContext securityContext;

	@Override
	public String getName() {
		return "get-ticket-price";
	}

	@Override
	public String getDescription() {
		return "Get ticket price for event";
	}

	@Override
	public User.UserRole getAllowedRole() {
		return User.UserRole.CLIENT;
	}

	@Override
	public void apply(BufferedReader reader, PrintStream out) {
		out.println("Enter event name:");
		String name = "";
		boolean isVipSeats = false;

		try {
			name = reader.readLine();
			Event event = eventService.getEventByName(name);

			out.println("Please choose seats (normal/VIP):");
			String seats = reader.readLine();
			if ("VIP".equalsIgnoreCase(seats)) {
				isVipSeats = true;
			}
			out.println("Enter event date (\"dd-MM-yyyy\"):");
			String eventDate = reader.readLine();
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(eventDate);


			User user = securityContext.getCurrentUser();

			String price = bookingService.getTicketPrice(event, date, isVipSeats, user);
			out.println("Price for event " + name + " is " + price + "$");

		} catch (IOException | ParseException | MovieException e) {
			out.println("Getting ticket price for event " + name + " produced an error " + e.getMessage());
		}
	}
}
