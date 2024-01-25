package com.springdb.jdbc.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
	private int id;
	private String name;
	private String phone;
	private int lastAccountRecordId;

	public User(int id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
}
