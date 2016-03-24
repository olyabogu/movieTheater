package com.epam.controller;

import com.epam.domain.Event;
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
    private EventService eventService;

    @RequestMapping(value = Mappings.ADD_EVENT, method = RequestMethod.GET)
    public String addEvent(ModelAndView mv) {
        mv.addObject("ratings", Event.Rating.values());
        return "addEvent";
    }

    /**
     * Add a new event
     * @param event
     * @return Redirect to /view-events page to display events list
     */
    @RequestMapping(value = Mappings.ADD_EVENT, method = RequestMethod.POST)
    public String add(@ModelAttribute("event") Event event) throws MovieException {
        eventService.createEvent(event);
        return "redirect:events";
    }

}
