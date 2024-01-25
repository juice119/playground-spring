package com.springdb.jdbc.service;

import com.springdb.jdbc.domain.AccountRecord;

public interface IAccountService {
	void deposit(int userId, int amount);

	AccountRecord withDrawl(int userId, int amount);

	AccountRecord transfer(int senderId, int receiverId, int amount);

	AccountRecord create(int userId);

	int getAmount(int userId);
}
