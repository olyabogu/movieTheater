package com.epam.services;

import com.epam.domain.Auditorium;
import com.epam.domain.Event;
import com.epam.exception.MovieException;

import java.util.Date;
import java.util.List;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
public interface EventService {

	Event createEvent(Event event) throws MovieException;

	void update(Event event) throws MovieException;

	void remove(int id) throws MovieException;

	Event getById(int id);

	Event getEventByName(String name) throws MovieException;

    List<Event> getAll();

    void assignAuditorium(Event event, Auditorium auditorium, Date date);

	String getAuditoriumForEvent(Event event, Date date);
}
