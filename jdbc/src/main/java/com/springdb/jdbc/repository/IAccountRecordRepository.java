package com.springdb.jdbc.repository;

import com.springdb.jdbc.domain.AccountRecord;

public interface IAccountRecordRepository {
	int findLastAmount(int userId);

	void save(AccountRecord accountRecord);
}
