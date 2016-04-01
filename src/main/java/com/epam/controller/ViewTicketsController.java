package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.controller.model.EventModel;
import com.epam.converter.EventConverter;
import com.epam.domain.Event;
import com.epam.services.EventService;

/**
 * @author Olga_Bogutska.
 */
@Controller
@PreAuthorize("hasRole('BOOKING_MANAGER')")
public class ViewTicketsController {

	@Autowired
	private EventService eventService;
	@Autowired
	private EventConverter eventConverter;

	@RequestMapping(value = Mappings.VIEW_TICKETS, method = RequestMethod.GET)
	public ModelAndView viewEvent(ModelAndView mv) {
		List<Event> events = eventService.getAll();
		List<EventModel> eventModels = eventConverter.toListEventModel(events);
		mv.addObject("events", eventModels);
		return mv;
	}
}
