package com.epam.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.controller.Mappings;
import com.epam.controller.model.UserModel;
import com.epam.converter.UserConverter;
import com.epam.domain.User;
import com.epam.services.UserAccountService;
import com.epam.services.UserService;

/**
 * @author Olga_Bogutska.
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserAccountService accountService;
	@Autowired
	private UserConverter userConverter;

	@RequestMapping(method = RequestMethod.GET, value = Mappings.USER + "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public
	@ResponseBody
	User get(@PathVariable String id) {
		return userService.getById(Integer.parseInt(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = Mappings.USER, produces = MediaType.APPLICATION_JSON_VALUE)
	public
	@ResponseBody
	User add(@RequestBody UserModel userModel) {
		User user = userConverter.toUser(userModel);
		int accountId = accountService.create(user.getAccount());
		user.getAccount().setId(accountId);
		userService.register(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.PUT, value = Mappings.USER + "/{id}")
	public
	@ResponseBody
	User update(
			@RequestBody UserModel userModel, @PathVariable String id) {
		User user = userConverter.toUser(userModel);
		userService.update(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = Mappings.USER + "/{id}")
	public
	@ResponseBody
	void remove(@PathVariable String id) {
		userService.remove(Integer.parseInt(id));
	}
}
