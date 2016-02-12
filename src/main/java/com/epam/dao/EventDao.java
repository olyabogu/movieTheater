package com.epam.dao;

import com.epam.domain.Auditorium;
import com.epam.domain.Event;
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
    }

    public void createEvent(Event event) {
        events.add(event);
    }

    public void remove(Event event) {
        if (events.contains(event)) {
            events.remove(event);
        } else {
            System.out.println("event " + event.toString() + " not exist");
        }
    }

    public Event getEventByName(String name) {
        for (Event event : events) {
            if (event.getName().equals(name)) {
                return event;
            }
        }
        return null;
    }

    public List<Event> getAll() {
        return events;
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {

    }
}