package com.epam.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.epam.controller.model.UserModel;
import com.epam.domain.User;
import com.epam.domain.UserAccount;
import com.epam.domain.UserRole;
import com.epam.exception.MovieException;

/**
 * @author Olga_Bogutska.
 */
@Component
public class UserConverter {

	public User toUser(UserModel userModel) {
		User user = new User();
		user.setId(userModel.getId());
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
		UserAccount account = new UserAccount();
		account.setAmount(Double.parseDouble(userModel.getBalance()));
		account.setCurrency(userModel.getCurrency());
		user.setAccount(account);
		user.setPassword(userModel.getPassword());
		List<String> roles = new ArrayList<>();
		roles.add(UserRole.REGISTERED_USER.name());
		user.setRoles(roles);
		return user;
	}
}
