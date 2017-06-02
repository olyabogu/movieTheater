package com.epam.converter;

import com.epam.controller.model.EventModel;
import com.epam.domain.Event;
import com.epam.domain.Rating;
import com.epam.exception.MovieException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;

/**
 * Created by Olga Bogutska on 3/24/2016.
 */
@Component
public class EventConverter {
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public Event toEvent(EventModel eventModel) throws MovieException {
        Event event = new Event();
        event.setId(eventModel.getId());
        event.setName(eventModel.getName());
        event.setBasePrice(parseDouble(eventModel.getBasePrice()));
	    List<String> dates = new ArrayList<>();
	    dates.add(eventModel.getDate());
        event.setDates(toDates(dates));
	    event.setRating(findRating(eventModel.getRating()));
        return event;
    }

	public EventModel toEventModel(Event event) {
		EventModel eventModel = new EventModel();
		eventModel.setId(event.getId());
		eventModel.setName(event.getName());
		eventModel.setBasePrice(event.getBasePrice().toString());
		eventModel.setRating(event.getRating().getDescription());
		eventModel.setDates(toStringDates(event.getDates()));
		eventModel.setTickets(CollectionUtils.isEmpty(event.getTickets()) ? 0 : event.getTickets().size());
		return eventModel;
	}

	public List<EventModel> toListEventModel(List<Event> events) {
		List<EventModel> models = new ArrayList<>();
		for (Event event : events) {
			models.add(toEventModel(event));
		}
		return models;
	}

	private Rating findRating(String modelRating) {
		for (Rating rating : Rating.values()) {
			if (rating.getDescription().equals(modelRating)) {
				return rating;
			}
		}
		return null;
	}

	private List<String> toStringDates(List<Date> eventDates) {
		List<String> listDates = new ArrayList<>();
		for (Date eventDate : eventDates) {
			String date = DATE_FORMAT.format(eventDate);
			listDates.add(date);
		}
		return listDates;
	}

	private List<Date> toDates(List<String> eventDates) {
		List<Date> listDates = new ArrayList<>();
		for (String eventDate : eventDates) {
			Date date;
			try {
				date = DATE_FORMAT.parse(eventDate);
			} catch (ParseException e) {
				throw new MovieException("Adding new event failed! Caused by " + e.getMessage());
			}
			listDates.add(date);
		}
		return listDates;
	}
}
