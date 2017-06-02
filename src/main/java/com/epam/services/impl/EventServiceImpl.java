package com.epam.services.impl;

import com.epam.dao.EventDao;
import com.epam.domain.Auditorium;
import com.epam.domain.Event;
import com.epam.exception.MovieException;
import com.epam.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDao dao;

    public Event createEvent(Event event) {
        if(event == null){
            throw new MovieException("Event didn't defined!");
        }
        return dao.create(event);
    }

	public void update(Event event) {
		if(event == null){
			throw new MovieException("Event didn't defined!");
		}
		dao.update(event);
	}

    public void remove(int id) {
	    if (id < 1) {
		    throw new MovieException("Event id is invalid!");
	    }
        dao.remove(id);
    }

    public Event getEventByName(String name) {
	    if (StringUtils.isEmpty(name)) {
		    throw new MovieException("Event name didn't defined!");
	    }
        return dao.getByName(name);
    }

	public Event getById(int id) {
		return dao.getById(id);
	}

    public List<Event> getAll() {
        return dao.getAll();
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
        dao.assignAuditorium(event, auditorium, date);
    }

	public String getAuditoriumForEvent(Event event, Date date) {
		return dao.getAuditoriumForEvent(event, date);
	}
}
