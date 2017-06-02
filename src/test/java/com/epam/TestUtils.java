package com.epam;

import static com.epam.converter.EventConverter.DATE_FORMAT;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.epam.controller.model.EventModel;
import com.epam.controller.model.UserModel;
import com.epam.domain.Currency;
import com.epam.domain.User;
import com.epam.domain.UserAccount;

/**
 * @author Olga_Bogutska.
 */
public class TestUtils {

	public static UserModel createTestUserModel(int id, String name, String password, String birthDate, String email) {
		UserModel model = new UserModel();
		model.setId(id);
		model.setName(name);
		model.setPassword(password);
		model.setBirthDate(birthDate);
		model.setEmail(email);
		model.setRole("REGISTERED_USER");
		model.setBalance("100.00");
		model.setCurrency("USD");
		return model;
	}

	public static User createTestUser(String name, String password, Date birthDate, String email) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setBirthDate(birthDate);
		user.setEmail(email);
		Set<String> roles = new HashSet<>();
		roles.add("REGISTERED_USER");
		user.setRoles(roles);
		UserAccount account = new UserAccount();
		account.setAmount(100.00);
		account.setCurrency(Currency.EUR.getDescription());
		user.setAccount(account);
		return user;
	}

	public static UserAccount createTestUserAccount(Double amount, Currency currency) {
		UserAccount account = new UserAccount();
		account.setAmount(amount);
		account.setCurrency(currency.getDescription());
		return account;
	}

	public static EventModel createTestEventModel(int id, String name, String basePrice, String rating, Date date, int size) {
		EventModel eventModel = new EventModel();
		eventModel.setId(id);
		eventModel.setName(name);
		eventModel.setBasePrice(basePrice);
		eventModel.setRating(rating);
		eventModel.setDate(DATE_FORMAT.format(date));
		eventModel.setTickets(size);
		return eventModel;
	}
}
