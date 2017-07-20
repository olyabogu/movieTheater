package com.epam.services.impl;

import com.epam.dao.UserDao;
import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.exception.MovieException;
import com.epam.services.UserAccountService;
import com.epam.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserAccountService accountService;

	public User getById(Integer id) {
		return userDao.getById(id);
	}

	public User getUserByEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			throw new MovieException("User email didn't defined!");
		}
		return userDao.getUserByEmail(email);
	}

	public User getUserByName(String name) {
		if (StringUtils.isEmpty(name)) {
			throw new MovieException("User name didn't defined!");
		}
		return userDao.getByName(name);
	}

	public List<Ticket> getBookedTickets(User user) {
		return userDao.getBookedTickets(user);
	}

	@Override
	public boolean isUserExists(String username, String email) {
		return userDao.isUserExists(username, email);
	}

	public void register(User user) {
		if (user == null) {
			throw new MovieException("User didn't defined");
		}
		setEncodedPassword(user);
		accountService.create(user.getAccount());
		userDao.create(user);
	}

	public void update(User user) {
		if (user == null) {
			throw new MovieException("User didn't defined");
		}
		setEncodedPassword(user);
		accountService.update(user.getAccount());
		userDao.update(user);
	}

	public void remove(int id) {
		if (id < 1) {
			throw new MovieException("User id not valid!");
		}
		int accountId = userDao.getById(id).getAccount().getId();
		userDao.remove(id);
		accountService.remove(accountId);
	}

	private void setEncodedPassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
}
