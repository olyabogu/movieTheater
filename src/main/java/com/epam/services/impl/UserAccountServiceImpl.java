package com.epam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.UserAccountDao;
import com.epam.domain.UserAccount;
import com.epam.exception.MovieException;
import com.epam.services.UserAccountService;

/**
 * @author Olga_Bogutska.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	private UserAccountDao accountDao;

	public int create(UserAccount account) {
		if (account == null) {
			throw new MovieException("UserAccount is not defined!");
		}
		return accountDao.create(account);
	}

	public void update(UserAccount account) {
		if (account == null) {
			throw new MovieException("UserAccount is not defined!");
		}
		accountDao.update(account);
	}

	public void remove(int id) {
		if (id < 1) {
			throw new MovieException("Account id not valid!");
		}
		accountDao.remove(id);
	}

	public UserAccount getById(int id) {
		return accountDao.getById(id);
	}
}
