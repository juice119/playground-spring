CREATE TABLE users(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	phone varchar(11) NOT NULL,
	name varchar(10) NOT NULL,
	last_account_record_id BIGINT,
	PRIMARY KEY(id),
	UNIQUE KEY(phone)
);


CREATE TABLE account_records(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	balance int NOT NULL,
	change_amount int NOT NULL,
	user_id BIGINT NOT NULL,
	transfer_user_id BIGINT,
	operation_type VARCHAR(20),
	PRIMARY KEY(id)
);
