package com.epam.services;

import com.epam.domain.UserAccount;

/**
 * @author Olga_Bogutska.
 */
public interface UserAccountService {

	int create(UserAccount account);

	void update(UserAccount account);

	void remove(UserAccount account);

	UserAccount getById(int id);
}
