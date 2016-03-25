package com.epam.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.epam.controller.model.UserModel;
import com.epam.domain.User;
import com.epam.domain.UserRole;
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
		user.setRole(findUserRole(userModel.getRole()));
		return user;
	}

	public UserModel toUserModel(User user) {
		UserModel model = new UserModel();
		model.setName(user.getName());
		model.setEmail(user.getEmail());
		model.setBirthDate(user.getBirthDate().toString());
		model.setRole(user.getRole().getDescription());
		return model;
	}

	private UserRole findUserRole(String modelUserRole) {
		for (UserRole rating : UserRole.values()) {
			if (rating.getDescription().equals(modelUserRole)) {
				return rating;
			}
		}
		return null;
	}
}
