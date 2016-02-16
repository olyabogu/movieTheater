package com.epam.services;

import com.epam.dao.EventDao;
import com.epam.domain.Auditorium;
import com.epam.domain.Event;
import com.epam.exception.MovieException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventDao dao;

    public void createEvent(Event event) {
        dao.createEvent(event);
    }

    public void remove(Event event) throws MovieException {
        dao.remove(event);
    }

    public Event getEventByName(String name) throws MovieException {
        return dao.getEventByName(name);
    }

    public List<Event> getAll() {
        return dao.getAll();
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
        dao.assignAuditorium(event, auditorium, date);
    }
}
