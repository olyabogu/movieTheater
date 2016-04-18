package com.epam.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.web.ws.user.GetUserResponse;

public class RunSoapClient {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ClientAppConfig.class);
		ctx.refresh();
		UserClient client = ctx.getBean(UserClient.class);
		System.out.println("For User Id: 1");
		GetUserResponse response = client.getUserById(1);
		System.out.println("Name:" + response.getUser().getUsername());
		System.out.println("Email:" + response.getUser().getEmail());
		System.out.println("Birth Date:" + response.getUser().getBirthDate());
	}
}
