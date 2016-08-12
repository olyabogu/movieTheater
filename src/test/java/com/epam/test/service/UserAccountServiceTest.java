package com.epam.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import com.epam.test.TestUtils;

/**
 * @author Olga_Bogutska.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfiguration.class, MvcConfiguration.class, SecurityConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAccountServiceTest {
	private UserAccount account;

	@Autowired
	private UserAccountService accountService;

	@Before
	public void setUp() {
		account = TestUtils.createTestUserAccount(1000.0, Currency.USD);
	}

	@Test
	public void testContext() {
		assertNotNull(accountService);
	}

	@Test
	public void testCreate() {
		int id = accountService.create(account);
		assertTrue(id > 0);
		account.setId(id);
		UserAccount userAccount = accountService.getById(id);
		assertEquals(account.getId(), userAccount.getId());
		assertEquals(account.getAmount(), userAccount.getAmount());
		assertEquals(account.getCurrency(), userAccount.getCurrency());
	}

	@Test(expected = MovieException.class)
	public void testCreateNull() {
		accountService.create(null);
	}

	@Test
	public void testGetById() {
		int id = accountService.create(account);
		UserAccount userAccount = accountService.getById(id);
		assertEquals(account.getAmount(), userAccount.getAmount());
		assertEquals(account.getCurrency(), userAccount.getCurrency());
	}

	@Test
	public void testUpdate() {

		int id = accountService.create(account);
		account.setId(id);
		Double amount = 2000.0;
		String currency = Currency.EUR.getDescription();
		account.setAmount(amount);
		account.setCurrency(currency);

		accountService.update(account);
		UserAccount updatedAccount = accountService.getById(id);

		assertEquals(amount, updatedAccount.getAmount());
		assertEquals(currency, updatedAccount.getCurrency());
	}

	@Test(expected = MovieException.class)
	public void testUpdateNull() {
		accountService.update(null);
	}

	@Test
	public void testRemove() {
		int id = accountService.create(account);
		account.setId(id);

		accountService.remove(account);
		UserAccount updatedAccount = accountService.getById(id);

		assertNull(updatedAccount);
	}

	@Test(expected = MovieException.class)
	public void testRemoveNull() {
		accountService.remove(null);
	}
}
