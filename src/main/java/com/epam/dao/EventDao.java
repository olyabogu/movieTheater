package com.epam.dao;

import com.epam.domain.Auditorium;
import com.epam.domain.Event;
import com.epam.exception.MovieException;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Repository
public class EventDao {
    private List<Event> events;

    @PostConstruct
    public void init() {
        events = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        dates.add(new Date());
        events.add(new Event(dates, 15.2, "movie 1", Event.Rating.HIGH));
        events.add(new Event(dates, 20.0, "movie 2", Event.Rating.MID));
        events.add(new Event(dates, 32.0, "movie 3", Event.Rating.LOW));
    }

    public void createEvent(Event event) {
        events.add(event);
    }

    public void remove(Event event) throws MovieException {
        if (events.contains(event)) {
            events.remove(event);
        } else {
	        throw new MovieException("Event " + event.toString() + " not exist");
        }
    }

    public Event getEventByName(String name) throws MovieException {
        for (Event event : events) {
            if (event.getName().equals(name)) {
                return event;
            }
        }
        throw new MovieException("Event with name " + name + " not exist");
    }

    public List<Event> getAll() {
        return events;
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {

    }
}