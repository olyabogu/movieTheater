package com.epam.test;

import com.epam.config.ApplicationConfiguration;
import com.epam.config.MvcConfiguration;
import com.epam.domain.Auditorium;
import com.epam.domain.Event;
import com.epam.domain.Rating;
import com.epam.exception.MovieException;
import com.epam.services.AuditoriumService;
import com.epam.services.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@WebAppConfiguration
@ContextConfiguration(classes={ApplicationConfiguration.class, MvcConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTest {
	private static final String NAME = "Event";

	@Autowired
    private EventService service;
	@Autowired
	private AuditoriumService auditoriumService;

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
	public void testCreate() throws MovieException {
		List<Date> dates = new ArrayList<>();
		dates.add(new Date());

		Event event = new Event(dates, 14.30D, NAME, Rating.valueOf("HIGH"));

		service.createEvent(event);
		event = service.getEventByName(NAME);

		assertTrue(event.getId() > 0);
	}

	@Test
	public void testUpdate() throws MovieException {
		Event event = service.getEventByName(NAME);
		Double newPrice = 15.0D;
		event.setBasePrice(newPrice);
		service.update(event);
		event = service.getEventByName(NAME);

		assertEquals(event.getBasePrice(), newPrice);
	}

	@Test
	public void testAssignAuditorium() throws MovieException {
		Event event = service.getEventByName("movie 1");
		Auditorium auditorium = auditoriumService.getAuditoriums().get(0);

		Date date = new Date();
		service.assignAuditorium(event, auditorium, date);
		String name = service.getAuditoriumForEvent(event, date);
		assertEquals(auditorium.getName(), name);
	}

    @Test
    public void testGetAll() {
        List<Event> events = service.getAll();
        assertNotNull(events);
        assertTrue(!CollectionUtils.isEmpty(events));
    }
}
