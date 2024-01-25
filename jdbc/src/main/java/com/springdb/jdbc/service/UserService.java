package com.springdb.jdbc.service;

import com.springdb.jdbc.domain.User;
import com.springdb.jdbc.repository.IUserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements IUserService {
	private IUserRepository userRepository;

	@Override
	public User findByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

	@Override
	public User create(String name, String phone) {
		int userId = userRepository.save(name, phone);
		return new User(userId, name, phone);
	}
}
