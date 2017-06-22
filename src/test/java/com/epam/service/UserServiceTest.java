package com.epam.service;

import com.epam.config.ApplicationConfiguration;
import com.epam.config.MvcConfiguration;
import com.epam.config.SecurityConfig;
import com.epam.domain.User;
import com.epam.domain.UserAccount;
import com.epam.domain.UserRole;
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
import static org.junit.Assert.assertTrue;

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
    }

    @Test
    public void testGetByName() {
        User user = service.getUserByName(EXIST_USER_NAME);
        assertNotNull(user);
        assertEquals(user.getUsername(), EXIST_USER_NAME);
    }

    @Test(expected = MovieException.class)
    public void testGetByEmptyName() {
        String name = "";
        service.getUserByName(name);
    }

    @Test
    public void testGetById() {
        int id = 1;
        User user = service.getById(id);
        assertNotNull(user);
        assertEquals(user.getId(), id);
    }

    @Test
    public void testGetByEmail() {
        User user = service.getUserByEmail(EXIST_USER_EMAIL);
        assertNotNull(user);
        assertEquals(user.getEmail(), EXIST_USER_EMAIL);
    }

    @Test(expected = MovieException.class)
    public void testGetByEmptyEmail() {
        String email = "";
        service.getUserByEmail(email);
    }

	@Test
	public void testRegister() {
		User user = createTestUser();
		int accountId = accountService.create(user.getAccount());
		user.getAccount().setId(accountId);
		service.register(user);
		User created = service.getById(user.getId());
		assertEquals(user, created);
	}

	@Test
	public void testUpdate() {
		User user = service.getUserByName(EXIST_USER_NAME);
		updateUser(user);
		accountService.update(user.getAccount());

		service.update(user);
		User updated = service.getById(user.getId());

		assertEquals(user, updated);
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

	public User createTestUser() {
		User user = new User();
		user.setName("Carl Robinson");
		user.setPassword("pass123");
		user.setBirthDate(new Date());
		user.setEmail("carl.rob@email.com");
		List<String> roles = new ArrayList<>();
		roles.add(UserRole.REGISTERED_USER.name());
		user.setRoles(roles);

		UserAccount account = new UserAccount();
		account.setAmount(100.00);
		account.setCurrency(com.epam.domain.Currency.EUR.getDescription());
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
		account.setCurrency(com.epam.domain.Currency.USD.getDescription());
		return user;
	}
}
