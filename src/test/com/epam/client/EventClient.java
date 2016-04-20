package com.epam.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.epam.web.ws.event.GetEventRequest;
import com.epam.web.ws.event.GetEventResponse;

/**
 * @author Olga_Bogutska.
 */
public class EventClient extends WebServiceGatewaySupport {

	public GetEventResponse getEventById(int eventId) {
		GetEventRequest request = new GetEventRequest();
		request.setEventId(eventId);
		GetEventResponse response = (GetEventResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/ws/event/getEventResponse"));
		return response;
	}
}
