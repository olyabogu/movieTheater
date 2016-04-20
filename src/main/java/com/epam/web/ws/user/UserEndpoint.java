package com.epam.web.ws.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epam.converter.UserGetRequestConverter;
import com.epam.services.UserService;

/**
 * @author Olga_Bogutska.
 */
@Endpoint
public class UserEndpoint {
	private static final String NAMESPACE_URI = "http://epam.com/web/ws/user";
	@Autowired
	private UserService userService;
	@Autowired
	private UserGetRequestConverter converter;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
		GetUserResponse response = new GetUserResponse();
		response.setUser(converter.convert(userService.getById(request.getUserId())));
		return response;
	}
}
