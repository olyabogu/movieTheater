package com.epam.service;

import com.epam.config.ApplicationConfiguration;
import com.epam.config.MvcConfiguration;
import com.epam.config.SecurityConfig;
import com.epam.domain.*;
import com.epam.domain.Currency;
import com.epam.exception.MovieException;
import com.epam.services.UserAccountService;
import com.epam.services.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@WebAppConfiguration
@ContextConfiguration(classes={ApplicationConfiguration.class, MvcConfiguration.class, SecurityConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	private static final String EXIST_USER_NAME = "defaultAdmin";
	private static final String EXIST_USER_EMAIL = "defaultAdmin@mail.com";
    @Autowired
    private UserService service;
	@Autowired
	private UserAccountService accountService;

    @Test
    public void testContext() {
        assertNotNull(service);
        assertNotNull(accountService);
    }

    @Test
    public void testGetByName() {
        User user = service.getUserByName(EXIST_USER_NAME);
        User existing = createExistingUser();
        assertNotNull(user);
        assertEquals(user, existing);
    }

    @Test(expected = MovieException.class)
    public void testGetByEmptyName() {
        String name = "";
        service.getUserByName(name);
    }

    @Test
    public void testGetById() {
        int id = 2;
        User user = service.getById(id);
	    User existing = createExistingUser();
        assertNotNull(user);
        assertEquals(user, existing);
    }

    @Test
    public void testGetByEmail() {
        User user = service.getUserByEmail(EXIST_USER_EMAIL);
	    User existing = createExistingUser();
        assertNotNull(user);
        assertEquals(user, existing);
    }

    @Test(expected = MovieException.class)
    public void testGetByEmptyEmail() {
        String email = "";
        service.getUserByEmail(email);
    }

	@Test
	public void testRegister() {
		User user = createTestUser("Carl Robinson", "carl.rob@email.com");
		int accountId = accountService.create(user.getAccount());
		user.getAccount().setId(accountId);
		service.register(user);
		User created = service.getById(user.getId());
		assertEquals(user, created);
	}

	@Test
	public void testUpdate() {
		User user = createTestUser("Emily Johnson", "emily.johnson@email.com");
		int accountId = accountService.create(user.getAccount());
		user.getAccount().setId(accountId);
		service.register(user);

		updateUser(user);
		service.update(user);
		User updated = service.getById(user.getId());

		assertEquals(user, updated);
	}

	@Test
	public void testRemove() {
		User user = createTestUser("Ann Johnson", "ann.johnson@email.com");
		service.register(user);

		service.remove(user.getId());
		User updated = service.getById(user.getId());
		UserAccount account = accountService.getById(user.getAccount().getId());

		assertNull(updated);
		assertNull(account);
	}

	@Test
	public void testIsUserExist() {
		boolean isUserExist = service.isUserExists(EXIST_USER_NAME, EXIST_USER_EMAIL);
		assertTrue(isUserExist);
	}

	@Test
	public void testIsUserExistByEmptyNameAndEmail() {
		boolean isUserExist = service.isUserExists("", "");
		assertFalse(isUserExist);
	}

	public User createTestUser(String name, String email) {
		User user = new User();
		user.setName(name);
		user.setPassword("pass123");
		user.setBirthDate(new Date());
		user.setEmail(email);
		List<String> roles = new ArrayList<>();
		roles.add(UserRole.REGISTERED_USER.name());
		user.setRoles(roles);

		UserAccount account = createAccount(0, 100.00, Currency.EUR.getDescription());
		user.setAccount(account);
		return user;
	}

	public User createExistingUser() {
		User user = new User();
		user.setId(2);
		user.setName(EXIST_USER_NAME);
		user.setPassword("default1A");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		Date date;
		try {
			date = formatter.parse("25-06-1987");
		} catch (ParseException e) {
			throw new MovieException("Adding new user failed! Caused by " + e.getMessage());
		}
		user.setBirthDate(date);
		user.setEmail(EXIST_USER_EMAIL);
		List<String> roles = new ArrayList<>();
		roles.add(UserRole.REGISTERED_USER.name());
		roles.add(UserRole.BOOKING_MANAGER.name());
		user.setRoles(roles);

		UserAccount account = createAccount(2, 8000.00, Currency.UAH.getDescription());
		user.setAccount(account);
		return user;
	}

	public User updateUser(User user) {
		user.setName("Bob Robinson");
		user.setPassword("qwe123");
		user.setBirthDate(new Date());
		user.setEmail("bob.robinson@email.com");
		List<String> roles = new ArrayList<>();
		roles.add(UserRole.BOOKING_MANAGER.name());
		roles.add(UserRole.REGISTERED_USER.name());
		user.setRoles(roles);

		UserAccount account = user.getAccount();
		account.setAmount(3000.00);
		account.setCurrency(Currency.USD.getDescription());
		return user;
	}

	private UserAccount createAccount(int id, Double amount, String currency) {
		UserAccount account = new UserAccount();
		account.setId(id);
		account.setAmount(amount);
		account.setCurrency(currency);
		return account;
	}
}
