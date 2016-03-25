package com.epam.controller;

import com.epam.controller.model.EventModel;
import com.epam.converter.EventConverter;
import com.epam.domain.Event;
import com.epam.domain.Rating;
import com.epam.exception.MovieException;
import com.epam.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Olga Bogutska.
 */
@Controller
public class AddEventController {

    @Autowired
    private EventConverter eventConverter;
    @Autowired
    private EventService eventService;

    @RequestMapping(value = Mappings.ADD_EVENT, method = RequestMethod.GET)
    public ModelAndView addEvent(ModelAndView mv) {
	    mv.addObject("ratings", Rating.values());
	    mv.setViewName("addEvent");
	    return mv;
    }

    /**
     * Add a new event
     * @param eventModel
     * @return Redirect to /events page to display events list
     */
    @RequestMapping(value = Mappings.ADD_EVENT, method = RequestMethod.POST)
    public String add(@ModelAttribute("eventModel") EventModel eventModel) throws MovieException {
        Event event = eventConverter.toEvent(eventModel);
        eventService.createEvent(event);
        return "redirect:viewEvents";
    }
}
