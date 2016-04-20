package com.epam.web.ws.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epam.converter.EventGetRequestConverter;
import com.epam.services.EventService;


/**
 * @author Olga_Bogutska.
 */
@Endpoint
public class EventEndpoint {
	private static final String NAMESPACE_URI = "http://epam.com/web/ws/event";
	@Autowired
	private EventService eventService;
	@Autowired
	private EventGetRequestConverter converter;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventRequest")
	@ResponsePayload
	public GetEventResponse getEvent(@RequestPayload GetEventRequest request) {
		GetEventResponse response = new GetEventResponse();
		response.setEvent(converter.convert(eventService.getById(request.getEventId())));
		return response;
	}
}
