package com.epam.services;

import com.epam.domain.Event;
import com.epam.domain.Ticket;
import com.epam.domain.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    public Double getTicketPrice(Event event, Date date, Date time, int seats, User user) {
        return event.getTicketPrice();
    }

    public boolean bookTicket(User user, Ticket ticket) {
        user.getBookedTickets().add(ticket);
        return true;
    }

    public List<Ticket> getTicketsForEvent(Event event, Date date) {
        return event.getTickets();
    }
}
