package com.epam.controller.rest;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.epam.config.ApplicationConfiguration;
import com.epam.config.MvcConfiguration;
import com.epam.config.SecurityConfig;
import com.epam.controller.Mappings;
import com.epam.controller.model.EventModel;
import com.epam.converter.EventConverter;
import com.epam.domain.Event;
import com.epam.domain.Rating;
import com.epam.TestUtils;

/**
 * @author Olga_Bogutska.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfiguration.class, MvcConfiguration.class, SecurityConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class EventRestTest {
	private static final String BASE_URI = "http://localhost:8080";
	private static final String URI = BASE_URI + Mappings.EVENT + "/{id}";

	private static final int ID = 3;
	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void testGetEvent() {
		Event event = restTemplate.getForObject(URI, Event.class, ID);
		Assert.assertEquals(ID, event.getId());
	}

	@Test
	public void testAddAndDeleteEvent() {
		EventModel model = TestUtils.createTestEventModel(0, "movie 12", "15.2", Rating.LOW.getDescription(), new Date(), 0);
		HttpEntity<EventModel> entity = new HttpEntity<>(model);
		ResponseEntity<Event> response = restTemplate.postForEntity(BASE_URI + Mappings.EVENT, entity, Event.class);
		Event eventResponse = response.getBody();

		Assert.assertEquals(model.getName(), eventResponse.getName());
		Assert.assertEquals(model.getBasePrice(), eventResponse.getBasePrice().toString());
		Assert.assertEquals(model.getRating(), eventResponse.getRating().getDescription());
		String date = EventConverter.DATE_FORMAT.format(eventResponse.getDates().get(0));
		Assert.assertEquals(model.getDate(), date);

		restTemplate.delete(URI, eventResponse.getId());
		eventResponse = restTemplate.getForObject(URI, Event.class, eventResponse.getId());
		Assert.assertNull(eventResponse);
	}

	@Test
	public void testUpdateEvent() {
		EventModel model = TestUtils.createTestEventModel(ID, "movie 14", "15.2", Rating.HIGH.getDescription(), new Date(), 0);
		HttpEntity<EventModel> entity = new HttpEntity<>(model);
		restTemplate.put(URI, entity, ID);
		Event event = restTemplate.getForObject(URI, Event.class, ID);

		Assert.assertEquals(model.getName(), event.getName());
		Assert.assertEquals(model.getBasePrice(), event.getBasePrice().toString());
		Assert.assertEquals(model.getRating(), event.getRating().getDescription());
		String date = EventConverter.DATE_FORMAT.format(event.getDates().get(0));
		Assert.assertEquals(model.getDate(), date);
	}

	@Test
	public void testGetNullEvent() {
		Event event = restTemplate.getForObject(URI, Event.class, 0);
		Assert.assertNull(event);
	}
}
