package com.epam.converter;

import com.epam.web.ws.event.Event;
import com.epam.web.ws.event.Rating;
import org.springframework.stereotype.Component;

/**
 * @author Olga_Bogutska.
 */
@Component
public class EventGetRequestConverter {

    public Event convert(com.epam.domain.Event event) {
        Event eventRequest = new Event();
        eventRequest.setId(event.getId());
        eventRequest.setName(event.getName());
        eventRequest.setRating(convertRating(event.getRating()));
        eventRequest.setBasePrice(event.getBasePrice());
        return eventRequest;
    }

    private Rating convertRating(com.epam.domain.Rating rating) {
        return Rating.valueOf(rating.getDescription().toUpperCase());
    }
}
