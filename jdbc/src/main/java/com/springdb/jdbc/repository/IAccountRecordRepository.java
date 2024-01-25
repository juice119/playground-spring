package com.springdb.jdbc.repository;

import com.springdb.jdbc.domain.AccountRecord;

public interface IAccountRecordRepository {

	AccountRecord findLastRecord(int userId);

	void save(AccountRecord accountRecord);
}
