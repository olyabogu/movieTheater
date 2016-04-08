package com.epam.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.controller.model.EventModel;
import com.epam.converter.EventConverter;
import com.epam.domain.Event;
import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.services.BookingService;
import com.epam.services.EventService;
import com.epam.util.SecurityUtils;

/**
 * @author Olga_Bogutska.
 */
@Controller
@PreAuthorize("hasRole('REGISTERED_USER')")
public class EventListController {

	@Autowired
	private EventService eventService;
	@Autowired
	private EventConverter eventConverter;
	@Autowired
	private BookingService bookingService;

	@RequestMapping(value = Mappings.VIEW_EVENTS, method = RequestMethod.GET)
	public ModelAndView listEvents() {
		ModelAndView modelAndView = new ModelAndView();
		List<Event> eventList = eventService.getAll();
		List<EventModel> eventModels = eventConverter.toListEventModel(eventList);
		modelAndView.addObject("events", eventModels);
		return modelAndView;
	}

	@RequestMapping(value = Mappings.BOOK_TICKET, method = RequestMethod.GET)
	public String bookTicket(@RequestParam("id") String id) {
		int eventId = Integer.parseInt(id);
		Event event = eventService.getById(eventId);
		User user = SecurityUtils.getLoggedUser();

		Double price = bookingService.getTicketPrice(event, new Date(), false, user);
		Ticket ticket = new Ticket();
		ticket.setPrice(price);

		bookingService.bookTicket(user, ticket);

		return "redirect:balance";
	}

	@RequestMapping(value = Mappings.VIEW_EVENTS, params = "back", method = RequestMethod.POST)
	public String cancelBookTicket() {
		return "redirect:index";
	}
}
