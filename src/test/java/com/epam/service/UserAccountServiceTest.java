package com.epam.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.epam.config.ApplicationConfiguration;
import com.epam.config.MvcConfiguration;
import com.epam.config.SecurityConfig;
import com.epam.domain.Currency;
import com.epam.domain.UserAccount;
import com.epam.exception.MovieException;
import com.epam.services.UserAccountService;

/**
 * @author Olga_Bogutska.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfiguration.class, MvcConfiguration.class, SecurityConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAccountServiceTest {
	private UserAccount account;
	private int id;

	@Autowired
	private UserAccountService accountService;

	@Before
	public void setUp() {
		account = createTestUserAccount(1000.0, Currency.USD);
		id = accountService.create(account);
	}

	@After
	public void tearDown() {
		accountService.remove(id);
	}

	@Test
	public void testContext() {
		assertNotNull(accountService);
	}

	@Test
	public void testCreate() {
		UserAccount userAccount = accountService.getById(id);
		assertEquals(account, userAccount);
	}

	@Test(expected = MovieException.class)
	public void testCreateNull() {
		accountService.create(null);
	}

	@Test
	public void testGetById() {
		UserAccount userAccount = accountService.getById(id);
		assertEquals(account, userAccount);
	}

	@Test
	public void testUpdate() {
		account.setAmount(2000.0);
		account.setCurrency(Currency.EUR.getDescription());
		accountService.update(account);
		UserAccount updatedAccount = accountService.getById(id);

		assertEquals(account, updatedAccount);
	}

	@Test(expected = MovieException.class)
	public void testUpdateNull() {
		accountService.update(null);
	}

	@Test
	public void testRemove() {
		accountService.remove(id);
		UserAccount updatedAccount = accountService.getById(id);

		assertNull(updatedAccount);
	}

	@Test(expected = MovieException.class)
	public void testRemoveNull() {
		accountService.remove(0);
	}

	public UserAccount createTestUserAccount(Double amount, Currency currency) {
		UserAccount account = new UserAccount();
		account.setAmount(amount);
		account.setCurrency(currency.getDescription());
		return account;
	}
}
