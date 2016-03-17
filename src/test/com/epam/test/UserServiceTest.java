package com.epam.test;

import com.epam.domain.User;
import com.epam.exception.MovieException;
import com.epam.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	private static final String NAME = "Carl";
    @Autowired
    private UserService service;

    @Test
    public void testContext() {
        assertNotNull(service);
    }

    @Test
    public void testGetByName() throws MovieException {
        String name = "Olga";
        User user = service.getUserByName(name);
        assertNotNull(user);
        assertEquals(user.getName(), name);
    }

    @Test(expected = MovieException.class)
    public void testGetByEmptyName() throws MovieException {
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
    public void testGetByEmail() throws MovieException {
        String email = "olga_bogu@mail.com";
        User user = service.getUserByEmail(email);
        assertNotNull(user);
        assertEquals(user.getEmail(), email);
    }

    @Test(expected = MovieException.class)
    public void testGetByEmptyEmail() throws MovieException {
        String email = "";
        service.getUserByEmail(email);
    }

	@Test
	public void testRegister() throws MovieException {
		User user = new User(NAME, new Date(), User.UserRole.CLIENT, NAME + "@email.com");
		service.register(user);
		user = service.getUserByName(NAME);
		assertTrue(user.getId() > 0);
	}

	@Test
	public void testUpdate() throws MovieException {
		User user = service.getUserByName(NAME);
		String newName = NAME + " 1";
		user.setName(newName);
		service.update(user);
		user = service.getUserByName(newName);

		assertEquals(user.getName(), newName);
	}
}
