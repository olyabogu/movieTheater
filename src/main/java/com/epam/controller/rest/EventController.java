package com.epam.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.controller.Mappings;
import com.epam.controller.model.EventModel;
import com.epam.converter.EventConverter;
import com.epam.domain.Event;
import com.epam.services.EventService;

/**
 * @author Olga_Bogutska.
 */
@RestController
public class EventController {
	@Autowired
	private EventConverter eventConverter;
	@Autowired
	private EventService eventService;

	@RequestMapping(method = RequestMethod.GET, value = Mappings.EVENT + "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
	Event get(@PathVariable String id) {
		return eventService.getById(Integer.parseInt(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = Mappings.EVENT, produces = MediaType.APPLICATION_JSON_VALUE)
	public
	@ResponseBody
	Event add(@RequestBody EventModel eventModel) {
		Event event = eventConverter.toEvent(eventModel);
		return eventService.createEvent(event);
	}

	@RequestMapping(method = RequestMethod.PUT, value = Mappings.EVENT + "/{id}")
	public
	@ResponseBody
	Event update(
			@RequestBody EventModel eventModel, @PathVariable String id) {
		Event event = eventConverter.toEvent(eventModel);
		eventService.update(event);
		return event;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = Mappings.EVENT + "/{id}")
	public
	@ResponseBody
	void remove(@PathVariable String id) {
		eventService.remove(Integer.parseInt(id));
	}
}
