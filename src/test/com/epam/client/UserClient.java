package com.epam.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.epam.web.ws.user.GetUserRequest;
import com.epam.web.ws.user.GetUserResponse;

public class UserClient extends WebServiceGatewaySupport {

	public GetUserResponse getUserById(int userId) {
		GetUserRequest request = new GetUserRequest();
		request.setUserId(userId);
		GetUserResponse response = (GetUserResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/ws/user/getUserResponse"));
		return response;
	}
}
