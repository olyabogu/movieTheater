package com.epam.test;

import com.epam.domain.Event;
import com.epam.exception.MovieException;
import com.epam.services.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTest {
    @Autowired
    private EventService service;

    @Test
    public void testContext() {
        assertNotNull(service);
    }

    @Test
    public void testGetEventByName() throws MovieException {
        String name = "movie 1";
        Event event = service.getEventByName(name);
        assertNotNull(event);
        assertEquals(event.getName(), name);
    }

    @Test(expected = MovieException.class)
    public void testGetEventByEmptyName() throws MovieException {
        String name = "";
        service.getEventByName(name);
    }

    @Test
    public void testGetAll(){
        List<Event> events = service.getAll();
        assertNotNull(events);
        assertNotEquals(events.size(), 0);
    }
}
