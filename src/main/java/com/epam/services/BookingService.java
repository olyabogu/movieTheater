package com.epam.services;

import java.util.Date;
import java.util.List;

import com.epam.domain.Event;
import com.epam.domain.Ticket;
import com.epam.domain.User;

/**
 * @author Olga_Bogutska.
 */
public interface BookingService {

	boolean bookTicket(User user, Ticket ticket);

	Double getTicketPrice(Event event, Date date, boolean isVipSeats, User user);

	List<Ticket> getTicketsForEvent(Event event, Date date);
}
