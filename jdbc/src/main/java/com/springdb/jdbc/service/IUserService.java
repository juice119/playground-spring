package com.springdb.jdbc.service;

import com.springdb.jdbc.domain.User;

public interface IUserService {
	User findByPhone(String phone);

	User create(String name, String phone);
}
