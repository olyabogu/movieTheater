package com.epam.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.epam.controller.model.UserModel;
import com.epam.domain.User;
import com.epam.exception.MovieException;

/**
 * @author Olga_Bogutska.
 */
@Component
public class UserConverter {

	public User toUser(UserModel userModel) throws MovieException {
		User user = new User();
		user.setName(userModel.getName());
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		Date date;
		try {
			date = formatter.parse(userModel.getBirthDate());
		} catch (ParseException e) {
			throw new MovieException("Adding new user failed! Caused by " + e.getMessage());
		}
		user.setBirthDate(date);
		user.setEmail(userModel.getEmail());
		user.setPassword(userModel.getPassword());
		Set<String> roles = new HashSet<>();
		roles.add("REGISTERED_USER");
		user.setRoles(roles);
		return user;
	}
}
