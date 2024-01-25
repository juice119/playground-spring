package com.springdb.jdbc.service;

import com.springdb.jdbc.domain.AccountRecord;
import com.springdb.jdbc.repository.IAccountRecordRepository;

public class AccountService implements IAccountService {
	private IAccountRecordRepository repository;

	public AccountService(IAccountRecordRepository repository) {
		this.repository = repository;
	}

	@Override
	public void deposit(int userId, int amount) {
		AccountRecord record = AccountRecord.createDeposit(0, amount, userId);
		repository.save(record);
	}

	@Override
	public AccountRecord withDrawl(int userId, int amount) {
		return null;
	}

	@Override
	public AccountRecord transfer(int senderId, int receiverId, int amount) {
		return null;
	}

	@Override
	public AccountRecord create(int userId) {
		return null;
	}

	@Override
	public int getAmount(int userId) {
		return repository.findLastAmount(userId);
	}
}
