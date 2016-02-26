package com.epam.services;

import com.epam.dao.impl.EventDaoImpl;
import com.epam.domain.Auditorium;
import com.epam.domain.Event;
import com.epam.exception.MovieException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventDaoImpl dao;

    public void createEvent(Event event) throws MovieException {
        if(event == null){
            throw new MovieException("Event didn't defined!");
        }
        dao.create(event);
    }

    public void remove(Event event) throws MovieException {
        if(event == null){
            throw new MovieException("Event didn't defined!");
        }
        dao.remove(event);
    }

    public Event getEventByName(String name) throws MovieException {
        if(StringUtils.isEmpty(name)){
            throw new MovieException("Event name didn't defined!");
        }
        return dao.getByName(name);
    }

    public List<Event> getAll() {
        return dao.getAll();
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
        dao.assignAuditorium(event, auditorium, date);
    }
}
