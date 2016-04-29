package com.epam.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.epam.controller.model.UserModel;
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
		account.setCurrency("USD");
		user.setAccount(account);
		return user;
	}
}
