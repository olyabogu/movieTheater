package com.epam.controller;

import java.util.ArrayList;
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
@PreAuthorize("hasRole('REGISTERED_USER')")
public class EventListController {

	@Autowired
	private EventService eventService;
	@Autowired
	private EventConverter eventConverter;

	@RequestMapping(value = Mappings.VIEW_EVENTS, method = RequestMethod.GET)
	public ModelAndView listEvents() {
		ModelAndView modelAndView = new ModelAndView();
		List<Event> eventList = eventService.getAll();
		List<EventModel> eventModels = new ArrayList<>();
		for (Event event : eventList) {
			EventModel model = eventConverter.toEventModel(event);
			eventModels.add(model);
		}
		modelAndView.addObject("events", eventModels);
		return modelAndView;
	}
}
