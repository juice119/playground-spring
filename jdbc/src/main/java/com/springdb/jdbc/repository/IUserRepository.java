package com.springdb.jdbc.repository;

import com.springdb.jdbc.domain.User;

public interface IUserRepository {
	int save(String name, String phone);

	User findByPhone(String phone);
}
