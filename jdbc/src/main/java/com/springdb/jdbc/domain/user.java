package com.springdb.jdbc.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class user {
	private int id;
	private String name;
	private String phone;
	private int lastAccountRecordId;
}
