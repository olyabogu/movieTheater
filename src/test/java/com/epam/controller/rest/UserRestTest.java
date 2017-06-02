package com.epam.controller.rest;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.epam.config.ApplicationConfiguration;
import com.epam.config.MvcConfiguration;
import com.epam.config.SecurityConfig;
import com.epam.controller.Mappings;
import com.epam.controller.model.UserModel;
import com.epam.domain.User;
import com.epam.controller.TestUtils;

/**
 * @author Olga_Bogutska.
 */

@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfiguration.class, MvcConfiguration.class, SecurityConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRestTest {

	private static final String BASE_URI = "http://localhost:8080";
	private static final String URI = BASE_URI + Mappings.USER + "/{id}";

	private static final int ID = 2;
	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void testGetUser() {
		int id = 1;
		User user = restTemplate.getForObject(URI, User.class, id);

		Assert.assertEquals(id, user.getId());
	}

	@Test
	public void testAddUser() {
		UserModel model = TestUtils.createTestUserModel(0, "Steve Newton Day", "pass", "10-12-1987", "name@email.com");
		HttpEntity<UserModel> entity = new HttpEntity<>(model);
		ResponseEntity<User> response = restTemplate.postForEntity(BASE_URI + Mappings.USER, entity, User.class);
		User userResponse = response.getBody();

		Assert.assertEquals(model.getName(), userResponse.getUsername());
		Assert.assertEquals(model.getEmail(), userResponse.getEmail());
		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = formatter.format(userResponse.getBirthDate());
		Assert.assertEquals(model.getBirthDate(), date);
	}

	@Test
	public void testUpdateUser() {
		UserModel model = TestUtils.createTestUserModel(ID, "Paul Martin Day", "pass123", "19-12-1987", "name@email.com");
		HttpEntity<UserModel> entity = new HttpEntity<>(model);
		restTemplate.put(URI, entity, ID);
		User user = restTemplate.getForObject(URI, User.class, ID);

		Assert.assertEquals(model.getName(), user.getUsername());
		Assert.assertEquals(model.getEmail(), user.getEmail());
		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = formatter.format(user.getBirthDate());
		Assert.assertEquals(model.getBirthDate(), date);
	}

	@Test
	public void testDeleteUser() {
		restTemplate.delete(URI, ID);
		User user = restTemplate.getForObject(URI, User.class, ID);
		Assert.assertNull(user);
	}
}
