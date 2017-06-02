package com.epam.service;

import com.epam.config.ApplicationConfiguration;
import com.epam.config.MvcConfiguration;
import com.epam.config.SecurityConfig;
import com.epam.domain.User;
import com.epam.exception.MovieException;
import com.epam.services.UserAccountService;
import com.epam.services.UserService;
import com.epam.controller.TestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
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
	private static final String NAME = "Carl Robinson";
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
        String name = "Olga";
        User user = service.getUserByName(name);
        assertNotNull(user);
        assertEquals(user.getUsername(), name);
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
        String email = "olga_bogu@mail.com";
        User user = service.getUserByEmail(email);
        assertNotNull(user);
        assertEquals(user.getEmail(), email);
    }

    @Test(expected = MovieException.class)
    public void testGetByEmptyEmail() {
        String email = "";
        service.getUserByEmail(email);
    }

	@Test
	public void testRegister() {
		User user = TestUtils.createTestUser(NAME, "pass123", new Date(), NAME + "@email.com");
		int accountId = accountService.create(user.getAccount());
		user.getAccount().setId(accountId);
		service.register(user);
		user = service.getUserByName(NAME);
		assertTrue(user.getId() > 0);
	}

	@Test
	public void testUpdate() {
		User user = service.getUserByName(NAME);
		String newName = NAME + " 1";
		user.setName(newName);
		service.update(user);
		user = service.getUserByName(newName);

		assertEquals(user.getUsername(), newName);
	}
}
