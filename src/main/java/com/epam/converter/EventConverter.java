package com.epam.converter;

import com.epam.controller.model.EventModel;
import com.epam.domain.Event;
import com.epam.exception.MovieException;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;

/**
 * Created by Olga Bogutska on 3/24/2016.
 */
@Component
public class EventConverter {

    public Event toEvent(EventModel eventModel) throws MovieException {
        Event event = new Event();
        event.setName(eventModel.getName());
        event.setBasePrice(parseDouble(eventModel.getBasePrice()));
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        List<Date> dates = new ArrayList<>();

        Date date;
        try {
            date = formatter.parse(eventModel.getDate());
        } catch (ParseException e) {
            throw new MovieException("Adding new event failed! Caused by " + e.getMessage());
        }
        dates.add(date);
        event.setRating(Event.Rating.valueOf(eventModel.getRating()));
        event.setDates(dates);
        return event;
    }
}
