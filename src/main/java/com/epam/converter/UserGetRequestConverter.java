package com.epam.converter;

import org.springframework.stereotype.Component;

import com.epam.web.ws.user.User;

/**
 * @author Olga_Bogutska.
 */
@Component
public class UserGetRequestConverter {

	public User convert(com.epam.domain.User user) {
		User userRequest = new User();
		userRequest.setId(user.getId());
		userRequest.setUsername(user.getUsername());
		userRequest.setPassword(user.getPassword());
		userRequest.setEmail(user.getEmail());

		return userRequest;
	}
}
