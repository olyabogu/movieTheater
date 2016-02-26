package com.epam.dao;

import com.epam.domain.Auditorium;
import com.epam.domain.Event;

import java.util.Date;
import java.util.List;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
public interface EventDao {

    List<Event> getAll();

    void assignAuditorium(Event event, Auditorium auditorium, Date date);
}
