package com.epam.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.client.EventClient;
import com.epam.client.UserClient;
import com.epam.web.ws.event.GetEventResponse;
import com.epam.web.ws.user.GetUserResponse;

public class RunSoapClient {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ClientAppConfig.class);
		ctx.refresh();

		testUserClient(ctx);
		testEventClient(ctx);
	}

	private static void testUserClient(AnnotationConfigApplicationContext ctx) {
		UserClient userClient = ctx.getBean(UserClient.class);
		System.out.println("For User Id: 1");
		GetUserResponse userResponse = userClient.getUserById(1);
		System.out.println("Name:" + userResponse.getUser().getUsername());
		System.out.println("Email:" + userResponse.getUser().getEmail());
		System.out.println("Birth Date:" + userResponse.getUser().getBirthDate());
	}

	private static void testEventClient(AnnotationConfigApplicationContext ctx) {
		EventClient eventClient = ctx.getBean(EventClient.class);
		System.out.println("For Event Id: 1");
		GetEventResponse eventResponse = eventClient.getEventById(1);
		System.out.println("Name:" + eventResponse.getEvent().getName());
		System.out.println("Base Price:" + eventResponse.getEvent().getBasePrice());
		System.out.println("Rating:" + eventResponse.getEvent().getRating());
	}
}
