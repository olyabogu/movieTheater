package com.epam.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientAppConfig {
	@Bean
	public Jaxb2Marshaller userMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.epam.web.ws.user");
		return marshaller;
	}

	@Bean
	public UserClient userClient(Jaxb2Marshaller userMarshaller) {
		UserClient client = new UserClient();
		client.setDefaultUri("http://localhost:8080/ws/user/users.wsdl");
		client.setMarshaller(userMarshaller);
		client.setUnmarshaller(userMarshaller);
		return client;
	}

	@Bean
	public Jaxb2Marshaller eventMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.epam.web.ws.event");
		return marshaller;
	}

	@Bean
	public EventClient eventClient(Jaxb2Marshaller eventMarshaller) {
		EventClient client = new EventClient();
		client.setDefaultUri("http://localhost:8080/ws/user/events.wsdl");
		client.setMarshaller(eventMarshaller);
		client.setUnmarshaller(eventMarshaller);
		return client;
	}
}
